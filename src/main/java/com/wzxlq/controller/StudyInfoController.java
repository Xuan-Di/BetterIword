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