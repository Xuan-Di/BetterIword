package com.wzxlq.controller;

import com.wzxlq.entity.User;
import com.wzxlq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    private  RedisTemplate<String,Object> redisTemplate;
    @GetMapping("queryUserByOpenId")
    private User queryUserByOpenId(HttpServletRequest request) {
        String openId = request.getHeader("token");
        return userService.queryById(openId);
    }
    @GetMapping("test")
    public User quess(String username){
        return userService.queryById(username);
    }
    @GetMapping("changeUserName")
    public User changeUserName(String name,HttpServletRequest request){
        String openId = request.getHeader("token");
        User user = userService.queryById(openId);
        user.setNickName(name);
        return userService.update(user);
    }
    @GetMapping("setDailyCount")
    public boolean setDailyCount(Integer dailyCount,HttpServletRequest request){
        String openId = request.getHeader("token");
        redisTemplate.opsForHash().put("User_"+openId,"dailyCount",dailyCount);
        return true;
    }
    @GetMapping("setRankImage")
    public boolean setRankImage(Integer dailyCount,HttpServletRequest request){
        String openId = request.getHeader("token");
        redisTemplate.opsForHash().put("User_"+openId,"rankImage",dailyCount);
        return true;
    }
    @GetMapping("setIsTiXing")
    public boolean setIsTiXing(Integer isTiXing,HttpServletRequest request){
        String openId = request.getHeader("token");
        if(isTiXing==1){
            redisTemplate.opsForHash().put("User_"+openId,"isTixing",1);
        }else{
            redisTemplate.opsForHash().put("User_"+openId,"isTixing",0);
        }
        return true;
    }
}