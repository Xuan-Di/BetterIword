package com.wzxlq.service;

import com.wzxlq.dto.WordInfoDTO;
import com.wzxlq.entity.Word;
import java.util.List;
import java.util.Map;

/**
 * (Word)表服务接口
 *
 * @author 王照轩
 * @since 2020-04-13 21:49:19
 */
public interface WordService {

    /**
     * 通过ID查询单条数据
     *
     * @param englishWord 主键
     * @return 实例对象
     */
    Word queryById(String englishWord);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Word> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param word 实例对象
     * @return 实例对象
     */
    Word insert(Word word);

    /**
     * 修改数据
     *
     * @param word 实例对象
     * @return 实例对象
     */
    Word update(Word word);

    List<Word> firstQueryWords(String openId);

    List<Word> queryTodayWords(String openId);


     void killWordMap();

    boolean killWordMapWithOpenId(String openId);

    List<Word> review(String openId);
    void analyzeStudyInfo();

    boolean wordInfo(WordInfoDTO knowWord, String openId);


    List<Word> wordCountTest();

    List<Word> queryByFuzzyMatching(String keyword);
}