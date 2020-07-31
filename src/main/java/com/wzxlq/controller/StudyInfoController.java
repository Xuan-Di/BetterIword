package com.wzxlq.controller;

import com.wzxlq.entity.StudyInfo;
import com.wzxlq.entity.Word;
import com.wzxlq.service.StudyInfoService;
import com.wzxlq.utils.GetContinuousSignInDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * (StudyInfo)表控制层
 *
 * @author makejava
 * @since 2020-04-25 10:53:48
 */
@RestController
@RequestMapping("studyInfo")
public class StudyInfoController {
    /**
     * 服务对象
     */
    @Resource
    private StudyInfoService studyInfoService;

    @GetMapping("queryStudyInfo")
    public List<StudyInfo> queryStudyInfo(HttpServletRequest request) {
        String openId = request.getHeader("token");
        return studyInfoService.queryStudyInfo(openId);
    }
    @GetMapping("querySignTime")
    public List<LocalDate> querySignTime(HttpServletRequest request){
        String openId = request.getHeader("token");
        return studyInfoService.querySignTime(openId);
    }
}