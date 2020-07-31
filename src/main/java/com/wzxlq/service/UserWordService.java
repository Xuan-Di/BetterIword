package com.wzxlq.service;

import com.wzxlq.entity.UserWord;
import java.util.List;

/**
 * (UserWord)表服务接口
 *
 * @author makejava
 * @since 2020-04-15 18:01:11
 */
public interface UserWordService {

    /**
     * 通过ID查询单条数据
     *
     * @param wordId 主键
     * @return 实例对象
     */
    UserWord queryById(Integer wordId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserWord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userWord 实例对象
     * @return 实例对象
     */
    UserWord insert(UserWord userWord);

    /**
     * 修改数据
     *
     * @param userWord 实例对象
     * @return 实例对象
     */
    UserWord update(UserWord userWord);

    /**
     * 通过主键删除数据
     *
     * @param wordId 主键
     * @return 是否成功
     */
    boolean deleteById(String englishWord,String openId);

     List<UserWord> queryAllByOpenId(String openId);
}