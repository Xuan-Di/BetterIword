package com.wzxlq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wzxlq.dto.WordInfoDTO;
import com.wzxlq.entity.StudyInfo;
import com.wzxlq.entity.Word;
import com.wzxlq.dao.WordDao;
import com.wzxlq.service.StudyInfoService;
import com.wzxlq.service.WordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

/**
 * (Word)表服务实现类
 *
 * @author makejava
 * @since 2020-04-13 21:49:19
 */
@Service("wordService")
public class WordServiceImpl implements WordService {
    @Resource
    private WordDao wordDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RankService rankService;
    @Autowired
    private StudyInfoService studyInfoService;


    public HashMap<String, List<Word>> todayWordMap = new HashMap<>();

    public HashMap<String, Queue<Word>> reviewMap = new HashMap<>();
    HashSet<String> keySet = new HashSet<>();
    public static final String CACHE_POOL = "cachePool";

    /**
     * 通过ID查询单条数据
     *
     * @param englishWord 主键
     * @return 实例对象
     */
    @Override
    public Word queryById(String englishWord) {
        return this.wordDao.queryById(englishWord);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Word> queryAllByLimit(int offset, int limit) {
        return this.wordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param word 实例对象
     * @return 实例对象
     */
    @Override
    public Word insert(Word word) {
        this.wordDao.insert(word);
        return word;
    }

    /**
     * 修改数据
     *
     * @param word 实例对象
     * @return 实例对象
     */
    @Override
    public Word update(Word word) {
        this.wordDao.update(word);
        return this.queryById(word.getEnglishWord());
    }

    //第一次注册的时候 推送单词 和redis加入单词
    @Override
    public List<Word> firstQueryWords(String openId) {
        //生成用户复习的单词队列
        Queue<Word> wordQueue = new PriorityQueue<>((a, b) -> b.getCollect() - a.getCollect());
        //先加入一个NULL单词用于后续判断是不是第一次背单词，如果是，就不会有复习单词弹出
        wordQueue.add(new Word("NULL", Integer.MAX_VALUE));
        //将用户的复习单词队列加入到复习的hashmap
        reviewMap.put(openId, wordQueue);
        Integer dailyCount = getDailyCount(openId);
        //弹出20个单词，并加入到复习队列
        List<Word> list = wordDao.queryAllByLimit(0, dailyCount);
        reviewMap.get(openId).addAll(list);
        //缓存今天的单词列表，当天下次访问单词就不从redis中取，直接从缓存map中取
        todayWordMap.put(openId, list);
        //redisTemplate.opsForList().leftPushAll(openId + CACHE_POOL, list);
        //初始化当天个人的学习情况(插入)
        StudyInfo studyInfo = new StudyInfo(openId, 0, LocalDate.now(), 0, 0, 0, 0);
        studyInfoService.insert(studyInfo);
        return list;
    }

    private Integer getDailyCount(String openId) {
        Integer dailyCount = (Integer) redisTemplate.opsForHash().get("User_" + openId, "dailyCount");
        dailyCount = dailyCount == null ? 20 : dailyCount;
        return dailyCount;
    }

    //每天背诵的单词
    @Override
    public List<Word> queryTodayWords(String openId) {
        List<Word> words = new ArrayList<>();
        //如果缓存map中没有该用户
        if (!todayWordMap.containsKey(openId)) {
            //根据openid获得每天背多少个单词
            int dailyCount = getDailyCount(openId);
            while (dailyCount > 0) {
                Object o = redisTemplate.opsForList().rightPop(openId + CACHE_POOL);
                if (o == null) {
                    break;
                }
                //TODO
                Word word = ((JSONObject) o).toJavaObject(Word.class);
                words.add(word);
                dailyCount--;
            }
            Integer wordIndex = (Integer) redisTemplate.opsForHash().get("User_" + openId, "wordIndex");
            wordIndex = wordIndex == null ? 20 : wordIndex;
            //wordIndex是用户的单词序号索引，dailyCount是还剩多少个单词需要从数据库中取。
            List<Word> wordsFromDB = wordDao.queryAllByLimit(wordIndex, dailyCount);
            if (wordsFromDB != null && !wordsFromDB.isEmpty()) {
                words.addAll(wordsFromDB);
            }
            //当用户的复习队列单词大于40时,每次都要remove,防止队列太长
            while (reviewMap.get(openId) != null && reviewMap.get(openId).size() > 40) {
                reviewMap.get(openId).remove();
            }
            //复习队列不为空时，将今天redis弹出的单词加入到复习队列中
            if (reviewMap.get(openId) != null) {
                reviewMap.get(openId).addAll(words);
            }
            //初始化今日学习情况
            StudyInfo studyInfo = new StudyInfo(openId, 0, LocalDate.now(), 0, 0, 0, 0);
            studyInfoService.insert(studyInfo);
            //今日单词缓存到map中，今日下次访问直接访问缓存map
            todayWordMap.put(openId, words);
        } else {
            //缓存map中有该用户，直接返回缓存单词
            List<Word> temp = todayWordMap.get(openId);
            if (temp != null) {
                words = temp;
            }
        }
        return words;
    }

    // 12点后杀死map和set
    @Override
    public void killWordMap() {
        todayWordMap.clear();
        for (String key : keySet) {
            redisTemplate.delete(key);
        }
        keySet.clear();
    }
    @Override
    //统计每个单词的情况
    public boolean wordInfo(WordInfoDTO wordInfo, String openId) {
        if (wordInfo == null) {
            return false;
        }
        //保存know,fuzzy,unknow这三个队列的名称,用于12点后清空redis的每个人的三个队列
        keySet.add(openId + wordInfo.getType());

        if ("know".equals(wordInfo.getType())) {
            //判断该单词是否已经出现在了unknow队列或者fuzzy队列
            Boolean isUnknowMember = redisTemplate.opsForSet().isMember(openId + "unknow", wordInfo.getWord());
            Boolean isFuzzyMember = redisTemplate.opsForSet().isMember(openId + "fuzzy", wordInfo.getWord());
            //如果该单词没有在unknow和fuzzy队列，那么才允许入know队列
            if(!isFuzzyMember && !isUnknowMember){
                redisTemplate.opsForSet().add(openId + wordInfo.getType(), wordInfo.getWord());
            }
            //因为类型是know，所以要从背单词缓存中删除该单词
            boolean remove = todayWordMap.get(openId).remove(wordInfo.getWord());
            Integer wordIndex = (Integer) redisTemplate.opsForHash().get("User_" + openId, "wordIndex");
            wordIndex = Optional.ofNullable(wordIndex).orElse(0);
            //背了一个单词，那么该用户的数据库单词索引要加1
            redisTemplate.opsForHash().put("User_" + openId, "wordIndex", wordIndex + 1);
            if (remove) {
                //增加排名1分
                rankService.incrScore("score", openId, 1);
            }
        }
        if("fuzzy".equals(wordInfo.getType())){
            Boolean isUnknowMember = redisTemplate.opsForSet().isMember(openId + "unknow", wordInfo.getWord());
            if(!isUnknowMember){
                redisTemplate.opsForSet().add(openId + wordInfo.getType(), wordInfo.getWord());
            }
        }
        if("unknow".equals(wordInfo.getType())){
            Boolean isFuzzyMember = redisTemplate.opsForSet().isMember(openId + "fuzzy", wordInfo.getWord());
            if(!isFuzzyMember){
                redisTemplate.opsForSet().add(openId + wordInfo.getType(), wordInfo.getWord());
            }
        }
        return true;
    }

    //  用户完成了今天的背单词任务
    @Override
    public boolean killWordMapWithOpenId(String openId) {
        //清空map中该用户的缓存
        todayWordMap.put(openId, null);
        //更新该用户的今日学习情况
        Long know = redisTemplate.opsForSet().size(openId + "know");
        Long unknow = redisTemplate.opsForSet().size(openId + "unknow");
        Long fizzy = redisTemplate.opsForSet().size(openId + "fuzzy");
        int knowNum = know.intValue();
        int unknowNum = unknow.intValue();
        int fuzzyNum = fizzy.intValue();
        StudyInfo studyInfo = new StudyInfo(openId, 1, LocalDate.now(), knowNum, knowNum, unknowNum, fuzzyNum);
        int update = studyInfoService.update(studyInfo);
        if (update == 0) {
            return false;
        } else {
            return true;
        }
    }

    //复习
    @Override
    public List<Word> review(String openId) {
        ArrayList<Word> list = new ArrayList<>();
        Queue<Word> wordQueue = reviewMap.get(openId);
        //若复习的单词数量小于20，则直接返回，不删除队列中的数据
        if (wordQueue.size() <= 20) {
            if (!wordQueue.isEmpty()) {
                list.addAll(wordQueue);
            }
            //数量为21说明是第一次背单词，那么清除NULL字符然后返回空数组
        } else if (wordQueue.size() == 21) {
            reviewMap.get(openId).poll();
            return list;
        } else {
            //数量大于21那么返回20个单词
            for (int i = 0; i < 20; i++) {
                list.add(wordQueue.poll());
            }
        }
        return list;
    }

    @Override
    public void analyzeStudyInfo() {
        Set<Map.Entry<String, List<Word>>> entrySet = todayWordMap.entrySet();
        //12点过后，没有打卡批量更新今日的学习情况
        for (Map.Entry<String, List<Word>> entry : entrySet) {
            if (entry.getValue() != null && entry.getValue().size() >= 0) {
                String openId = entry.getKey();
                Long know = redisTemplate.opsForSet().size(openId + "know");
                Long unknow = redisTemplate.opsForSet().size(openId + "unknow");
                Long fizzy = redisTemplate.opsForSet().size(openId + "fuzzy");
                int knowNum = know.intValue();
                int unknowNum = unknow.intValue();
                int fuzzyNum = fizzy.intValue();
                //将今天未背的单词加入到个人redis缓冲池中
                if (!entry.getValue().isEmpty()) {
                    redisTemplate.opsForList().leftPushAll(openId + CACHE_POOL, entry.getValue());
                }
                //未打卡，12点更新学习情况
                StudyInfo studyInfo = new StudyInfo(openId, 0, LocalDate.now(), knowNum, knowNum, unknowNum, fuzzyNum);
                studyInfoService.update(studyInfo);
            }
        }
    }
    //词汇量测试
    @Override
    public List<Word> wordCountTest() {
        return wordDao.wordCountTest();
    }

    @Override
    //模糊查询单词
    public List<Word> queryByFuzzyMatching(String keyword) {
        return wordDao.queryByFuzzyMatching(keyword);
    }
}