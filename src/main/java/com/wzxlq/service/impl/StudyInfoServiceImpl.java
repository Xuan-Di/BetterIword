package com.wzxlq.service.impl;

import com.wzxlq.entity.StudyInfo;
import com.wzxlq.dao.StudyInfoDao;
import com.wzxlq.entity.Word;
import com.wzxlq.service.StudyInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * (StudyInfo)表服务实现类
 *
 * @author 李倩
 */
@Service("studyInfoService")
public class StudyInfoServiceImpl implements StudyInfoService {
    @Resource
    private StudyInfoDao studyInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StudyInfo queryById(Integer id) {
        return this.studyInfoDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param studyInfo 实例对象
     * @return 实例对象
     */
    @Override
    public StudyInfo insert(StudyInfo studyInfo) {
        this.studyInfoDao.insert(studyInfo);
        return studyInfo;
    }

    /**
     * 修改数据
     *
     * @param studyInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int update(StudyInfo studyInfo) {
          return  this.studyInfoDao.update(studyInfo);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.studyInfoDao.deleteById(id) > 0;
    }

    @Override
    public List<StudyInfo> queryStudyInfo(String openId) {
        return studyInfoDao.queryStudyInfo(openId);
    }

    @Override
    public List<LocalDate> querySignTime(String openId) {
        return studyInfoDao.querySignTime(openId);
    }

}