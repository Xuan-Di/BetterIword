package com.wzxlq.service.impl;

import com.wzxlq.dto.WordInfoDTO;
import com.wzxlq.entity.StudyInfo;
import com.wzxlq.entity.Word;
import com.wzxlq.dao.WordDao;
import com.wzxlq.exception.OpenIdNULLException;
import com.wzxlq.service.StudyInfoService;
import com.wzxlq.service.WordService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private RestHighLevelClient restHighLevelClient;
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
        Integer dailyCount = (Integer) redisTemplate.opsForHash().get("User_" + openId, "dailyCount");
        dailyCount = dailyCount == null ? 20 : dailyCount;
        //弹出20个单词，并加入到复习队列
        List<Word> list = wordDao.queryAllByLimit(0, dailyCount);
        redisTemplate.opsForHash().put("User_" + openId, "wordIndex", 20);
        reviewMap.get(openId).addAll(list);
        //缓存今天的单词列表，当天下次访问单词就不从redis中取，直接从缓存map中取
        todayWordMap.put(openId, list);
        //redisTemplate.opsForList().leftPushAll(openId + CACHE_POOL, list);
        //初始化当天个人的学习情况(插入)
        StudyInfo studyInfo = new StudyInfo(openId, 0, LocalDate.now(), 0, 0, 0, 0);
        studyInfoService.insert(studyInfo);
        return list;
    }

    //每天背诵的单词
    @Override
    public List<Word> queryTodayWords(String openId) {
        List<Word> words = new ArrayList<>();
        //如果缓存map中没有该用户
        if (!todayWordMap.containsKey(openId)) {
            Integer dailyCount = (Integer) redisTemplate.opsForHash().get("User_" + openId, "dailyCount");
            dailyCount = dailyCount == null ? 20 : dailyCount;
            while (dailyCount > 0) {
                Word o = (Word) redisTemplate.opsForList().rightPop(openId + CACHE_POOL);
                if (o == null) {
                    break;
                }
                words.add(o);
                dailyCount--;
            }
            Integer wordIndex = (Integer) redisTemplate.opsForHash().get("User_" + openId, "wordIndex");
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
    public boolean wordInfo(WordInfoDTO wordInfo, String openId) {
        if (wordInfo == null) {
            return false;
        }
        keySet.add(openId + wordInfo.getType());
        redisTemplate.opsForSet().add(openId + wordInfo.getType(), wordInfo.getWord());
        if ("know".equals(wordInfo.getType())) {
            boolean remove = todayWordMap.get(openId).remove(wordInfo.getWord());
            //redisTemplate.opsForList().remove(openId + CACHE_POOL, 1, wordInfo.getWord());
            Integer wordIndex = (Integer) redisTemplate.opsForHash().get("User_" + openId, "wordIndex");
            wordIndex = Optional.ofNullable(wordIndex).orElse(0);
            redisTemplate.opsForHash().put("User_" + openId, "wordIndex", wordIndex + 1);
            if (remove) {
                //增加排名
                rankService.incrScore("score", openId, 1);
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



    //在ES中查询
    @Override
    public List<Map<String, Object>> queryInEs(String keyword) {
        SearchRequest searchRequest = new SearchRequest("iword");
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //查询条件，我们可以使用queryBuilders工具来实现
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery("englishWord", "*" + keyword + "*"));
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit documentFields : search.getHits().getHits()) {

            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

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

    @Override
    public List<Word> wordCountTest() {
        return wordDao.wordCountTest();
    }
}