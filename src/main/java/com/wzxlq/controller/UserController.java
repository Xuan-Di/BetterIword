package com.wzxlq.controller;

import com.wzxlq.dto.NearByPersonDTO;
import com.wzxlq.dto.ResponseDto;
import com.wzxlq.entity.User;
import com.wzxlq.service.UserService;
import com.wzxlq.utils.GeoHashUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-04-15 17:07:54
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private GeoHashUtil geoHashUtil;

    @GetMapping("queryNearbyPerson")
    public ResponseDto queryNearbyPerson(HttpServletRequest request) {
        //String openId = request.getHeader("token");
        List<User> users = userService.queryAll();
        List<String> openIdList = new ArrayList<>();
        for (User user : users) {
            System.out.println(user.getOpenId());
            openIdList.add(user.getOpenId());
        }
        List<Point> points = geoHashUtil.redisGeoGet("location", openIdList);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(points);
        return responseDto;
    }

    @GetMapping("queryUserByOpenId")
    private User queryUserByOpenId(HttpServletRequest request) {
        String openId = request.getHeader("token");
        return userService.queryById(openId);
    }

    @GetMapping("test")
    public User quess(String username) {
        return userService.queryById(username);
    }

    @GetMapping("changeUserName")
    public User changeUserName(String name, HttpServletRequest request) {
        String openId = request.getHeader("token");
        User user = userService.queryById(openId);
        user.setNickName(name);
        return userService.update(user);
    }

    @GetMapping("setDailyCount")
    public boolean setDailyCount(Integer dailyCount, HttpServletRequest request) {
        String openId = request.getHeader("token");
        redisTemplate.opsForHash().put("User_" + openId, "dailyCount", dailyCount);
        return true;
    }

    @GetMapping("setRankImage")
    public boolean setRankImage(Integer dailyCount, HttpServletRequest request) {
        String openId = request.getHeader("token");
        redisTemplate.opsForHash().put("User_" + openId, "rankImage", dailyCount);
        return true;
    }

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