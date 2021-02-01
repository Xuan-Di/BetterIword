package com.wzxlq.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Package: com.wzxlq.util
 * @ClassName: MatchQueue
 * @Author: 王照轩
 * @CreateTime: 2021/1/10 16:46
 * @Description:
 */
@Data
@Component
public class MatchQueue {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String queueName = "MATCHQUEUE";
    private int size = 0;

    public synchronized void enterQueue(String username) {
        redisTemplate.opsForList().leftPush(queueName, username);
        size++;
    }

    public synchronized String exitQueue() {
        Object o = redisTemplate.opsForList().rightPop(queueName);
        size--;
        return (String) o;
    }

    public synchronized int cancelQueue(String username){
        Long result = redisTemplate.opsForList().remove(queueName, 0, username);
        size--;
        return result.intValue();
    }
    public int getSize(){
        return size;
    }
}
