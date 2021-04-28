package com.wzxlq.controller;

import com.wzxlq.entity.StudyInfo;
import com.wzxlq.service.StudyInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * (StudyInfo)表控制层
 *
 * @author 李倩
 * 学习情况的接口
 */
@RestController
@RequestMapping("studyInfo")
public class StudyInfoController {
    @Resource
    private StudyInfoService studyInfoService;
    /**
     * 功能描述:查询我的学习情况
     * @param request
     * @return java.util.List<com.wzxlq.entity.StudyInfo>
     */
    @GetMapping("queryStudyInfo")
    public List<StudyInfo> queryStudyInfo(HttpServletRequest request) {
        String openId = request.getHeader("token");
        return studyInfoService.queryStudyInfo(openId);
    }

    /**
     * 功能描述 :查询签到日期数组
     * @param request
     * @return java.util.List<java.time.LocalDate>
     */
    @GetMapping("querySignTime")
    public List<LocalDate> querySignTime(HttpServletRequest request){
        String openId = request.getHeader("token");
        return studyInfoService.querySignTime(openId);
    }
}