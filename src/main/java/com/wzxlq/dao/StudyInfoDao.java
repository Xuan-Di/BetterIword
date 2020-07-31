package com.wzxlq.dao;

import com.wzxlq.entity.StudyInfo;
import com.wzxlq.entity.Word;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * (StudyInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-25 10:53:48
 */
public interface StudyInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StudyInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StudyInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param studyInfo 实例对象
     * @return 对象列表
     */
    List<StudyInfo> queryAll(StudyInfo studyInfo);

    /**
     * 新增数据
     *
     * @param studyInfo 实例对象
     * @return 影响行数
     */
    int insert(StudyInfo studyInfo);

    /**
     * 修改数据
     *
     * @param studyInfo 实例对象
     * @return 影响行数
     */
    int update(StudyInfo studyInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<StudyInfo> queryStudyInfo(String openId);

    List<LocalDate> querySignTime(String openId);

}