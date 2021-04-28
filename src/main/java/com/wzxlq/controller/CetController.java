package com.wzxlq.controller;

import com.alibaba.fastjson.JSONObject;
import com.wzxlq.entity.TingLi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

/**
 * @author 李倩
 * @date 2020/6/3 - 10:59
 * 四六级听力接口
 */
@RestController
@RequestMapping("cet")
public class CetController {
    @Autowired
    public RedisTemplate<String, Object> redisTemplate;
    /**
     * 功能描述
     * 从redis中获得所有四级听力列表
     * @param
     * @return java.util.ArrayList<java.lang.String>
     */
    @GetMapping("tingLiFourList")
    public ArrayList<String> tingLiFourList() {
        ArrayList<String> list = new ArrayList<>();
        Set set = redisTemplate.opsForHash().keys("cet4");
        for (Object o : set) {
            list.add(o.toString());
        }
        //根据年限进行排序,近些年的排在前面
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.substring(0, 4).compareTo(o1.substring(0, 4));
            }
        });
        return list;
    }
    /**
     * 功能描述
     * 从redis中获得指定四级听力内容
     * @param title
     * @return com.wzxlq.entity.TingLi
     */
    @GetMapping("tingLiFourOne")
    public TingLi tingLiFourOne(String title) {
        Object tl4 =  redisTemplate.opsForHash().get("cet4", title);
        TingLi tl = null;
        if (tl4 != null) {
            //将Object对象强转为TingLi对象
            tl = ((JSONObject) tl4).toJavaObject(TingLi.class);
        }
        return tl;
    }
 /**
     * 功能描述
     * 从redis中获得所有六级听力列表
     * @param
     * @return java.util.ArrayList<java.lang.String>
     */
    @GetMapping("tingLiSixList")
    public ArrayList<String> tingLiSixList() {
        ArrayList<String> list = new ArrayList<>();
        Set set = redisTemplate.opsForHash().keys("cet6");
        for (Object o : set) {
            list.add(o.toString());
        }
        //根据年限进行排序,近些年的排在前面
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.substring(0, 4).compareTo(o1.substring(0, 4));
            }
        });
        return list;
    }
    /**
     * 功能描述
     * 从redis中获得指定六级听力内容
     * @param title
     * @return com.wzxlq.entity.TingLi
     */
    @GetMapping("tingLiSixOne")
    public TingLi tingLiSixOne(String title) {
        System.out.println(title);
        Object tl6 =  redisTemplate.opsForHash().get("cet6", title);
        TingLi tl = null;
        if (tl6 != null) {
            tl = ((JSONObject) tl6).toJavaObject(TingLi.class);
        }
        return tl;
    }
}
