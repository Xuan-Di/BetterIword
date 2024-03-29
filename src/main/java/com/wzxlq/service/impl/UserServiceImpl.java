package com.wzxlq.service.impl;

import com.wzxlq.entity.User;
import com.wzxlq.dao.UserDao;
import com.wzxlq.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author 李倩
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param openId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String openId) {
        return this.userDao.queryById(openId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getOpenId());
    }

    /**
     * 通过主键删除数据
     *
     * @param openId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String openId) {
        return this.userDao.deleteById(openId) > 0;
    }

    @Override
    public List<User> queryAll() {
        return userDao.queryAll(null);
    }
}