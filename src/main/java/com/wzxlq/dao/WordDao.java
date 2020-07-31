package com.wzxlq.dao;

import com.wzxlq.entity.Word;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Word)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-13 21:49:19
 */
public interface WordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param englishWord 主键
     * @return 实例对象
     */
    Word queryById(String englishWord);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Word> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Word> queryAll();

    /**
     * 新增数据
     *
     * @param word 实例对象
     * @return 影响行数
     */
    int insert(Word word);

    /**
     * 修改数据
     *
     * @param word 实例对象
     * @return 影响行数
     */
    int update(Word word);


    List<Word> wordCountTest();
}