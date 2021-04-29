package com.wzxlq.controller;

import com.wzxlq.entity.StudyInfo;
import com.wzxlq.service.StudyInfoService;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 功能描述:查询我的学习情况
     *
     * @param request
     * @return java.util.List<com.wzxlq.entity.StudyInfo>
     */
    @GetMapping("queryStudyInfo")
    public List<StudyInfo> queryStudyInfo(HttpServletRequest request) {
        String openId = request.getHeader("token");
        Long know = redisTemplate.opsForSet().size(openId + "know");
        Long unknow = redisTemplate.opsForSet().size(openId + "unknow");
        Long fuzzy = redisTemplate.opsForSet().size(openId + "fuzzy");
        int knowNum=0;
        int unknowNum=0;
        int fuzzyNum=0;
        if (know != null) {
             knowNum = know.intValue();
        }
        if (unknow != null) {
            unknowNum = unknow.intValue();
        }
        if (fuzzy != null) {
            fuzzyNum = fuzzy.intValue();

        }
        //每次查询学习情况的之前实时更新一下当天的学习情况
        StudyInfo studyInfo = new StudyInfo(openId, LocalDate.now(), knowNum, knowNum, unknowNum, fuzzyNum);
        studyInfoService.update(studyInfo);
        return studyInfoService.queryStudyInfo(openId);
    }

    /**
     * 功能描述 :查询签到日期数组
     *
     * @param request
     * @return java.util.List<java.time.LocalDate>
     */
    @GetMapping("querySignTime")
    public List<LocalDate> querySignTime(HttpServletRequest request) {
        String openId = request.getHeader("token");
        return studyInfoService.querySignTime(openId);
    }
}