package com.wzxlq.controller;

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
 * @author 王照轩
 * @date 2020/6/3 - 10:59
 */
@RestController
@RequestMapping("cet")
public class CetController {
    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    @GetMapping("tingLiFourList")
    public ArrayList<String> tingLiFourList() {
        ArrayList<String> list = new ArrayList<>();
        Set set = redisTemplate.opsForHash().keys("cet4");
        for (Object o : set) {
            list.add(o.toString());
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.substring(0, 4).compareTo(o1.substring(0, 4));
            }
        });
        return list;
    }

    @GetMapping("tingLiFourOne")
    public TingLi tingLiFourOne(String title){
         TingLi tl = (TingLi)redisTemplate.opsForHash().get("cet4",title);
         return tl;
    }
    @GetMapping("tingLiSixList")
    public ArrayList<String> tingLiSixList() {
        ArrayList<String> list = new ArrayList<>();
        Set set = redisTemplate.opsForHash().keys("cet6");
        for (Object o : set) {
            list.add(o.toString());
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.substring(0, 4).compareTo(o1.substring(0, 4));
            }
        });
        return list;
    }

    @GetMapping("tingLiSixOne")
    public TingLi tingLiSixOne(String title){
        System.out.println(title);
         TingLi tl = (TingLi)redisTemplate.opsForHash().get("cet6",title);
        System.out.println(tl.getTitle());
         return tl;
    }
}
