package com.wzxlq.controller;

import com.wzxlq.dto.ResponseDto;
import com.wzxlq.entity.User;
import com.wzxlq.service.UserService;
import com.wzxlq.utils.GeoHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表控制层
 *  用户接口:包括获得定位,设置背单词提醒,设置每天背多少单词
 * @author 李倩
 * @since 2021-04-15 17:07:54
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private GeoHashUtil geoHashUtil;
    /**
     * 功能描述 :获得所有人的定位
     * @param request
     * @return com.wzxlq.dto.ResponseDto
     */
    @GetMapping("queryNearbyPerson")
    public ResponseDto queryNearbyPerson(HttpServletRequest request) {
        List<User> users = userService.queryAll();
        List<String> openIdList = new ArrayList<>();
        //获得所有用户的openid数组
        for (User user : users) {
            openIdList.add(user.getOpenId());
        }
        //根据openid数组获得所有用户的定位数组
        List<Point> points = geoHashUtil.redisGeoGet("location", openIdList);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(points);
        return responseDto;
    }
    /**
     * 功能描述:根据openid查询User对象,以获得头像等信息
     * @param request
     * @return com.wzxlq.entity.User
     */
    @GetMapping("queryUserByOpenId")
    private User queryUserByOpenId(HttpServletRequest request) {
        String openId = request.getHeader("token");
        return userService.queryById(openId);
    }
    /**
     * 功能描述 :设置每天背多少单词
     * @param dailyCount
     * @param request
     * @return boolean
     */
    @GetMapping("setDailyCount")
    public boolean setDailyCount(Integer dailyCount, HttpServletRequest request) {
        String openId = request.getHeader("token");
        redisTemplate.opsForHash().put("User_" + openId, "dailyCount", dailyCount);
        return true;
    }

    /**
     * 功能描述 :设置是否提醒背单词
     * @param isTiXing
     * @param request
     * @return boolean
     */
    @GetMapping("setIsTiXing")
    public boolean setIsTiXing(Integer isTiXing, HttpServletRequest request) {
        String openId = request.getHeader("token");
        if (isTiXing == 1) {
            redisTemplate.opsForHash().put("User_" + openId, "isTixing", 1);
        } else {
            redisTemplate.opsForHash().put("User_" + openId, "isTixing", 0);
        }
        return true;
    }
}