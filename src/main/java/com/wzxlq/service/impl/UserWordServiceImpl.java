package com.wzxlq.service.impl;

import com.wzxlq.entity.UserWord;
import com.wzxlq.dao.UserWordDao;
import com.wzxlq.service.UserWordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserWord)表服务实现类
 *
 * @author 李倩
 */
@Service("userWordService")
public class UserWordServiceImpl implements UserWordService {
    @Resource
    private UserWordDao userWordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param wordId 主键
     * @return 实例对象
     */
    @Override
    public UserWord queryById(Integer wordId) {
        return this.userWordDao.queryById(wordId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserWord> queryAllByLimit(int offset, int limit) {
        return this.userWordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userWord 实例对象
     * @return 实例对象
     */
    @Override
    public UserWord insert(UserWord userWord) {
        this.userWordDao.insert(userWord);
        return userWord;
    }

    /**
     * 修改数据
     *
     * @param userWord 实例对象
     * @return 实例对象
     */
    @Override
    public UserWord update(UserWord userWord) {
        this.userWordDao.update(userWord);
        return this.queryById(userWord.getWordId());
    }

    /**
     * 通过主键删除数据
     *
     * @param wordId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String englishWord, String openId) {
        return this.userWordDao.deleteById(englishWord,openId) > 0;
    }

    @Override
    public List<UserWord> queryAllByOpenId(String openId) {
        return userWordDao.queryAllByOpenId(openId);
    }
}