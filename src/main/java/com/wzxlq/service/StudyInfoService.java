package com.wzxlq.service;

import com.wzxlq.entity.StudyInfo;
import com.wzxlq.entity.Word;

import java.time.LocalDate;
import java.util.List;

/**
 * (StudyInfo)表服务接口
 *
 * @author makejava
 * @since 2020-04-25 10:53:48
 */
public interface StudyInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StudyInfo queryById(Integer id);


    /**
     * 新增数据
     *
     * @param studyInfo 实例对象
     * @return 实例对象
     */
    StudyInfo insert(StudyInfo studyInfo);

    /**
     * 修改数据
     *
     * @param studyInfo 实例对象
     * @return 实例对象
     */
    int update(StudyInfo studyInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<StudyInfo> queryStudyInfo(String openId);

    List<LocalDate> querySignTime(String openId);

}