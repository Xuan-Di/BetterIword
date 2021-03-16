package com.wzxlq.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MatchQueue {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String queueName = "MATCHQUEUE";

    public synchronized void enterQueue(String username) {
        redisTemplate.opsForList().leftPush(queueName, username);
        log.info(username+" 进入队列");
    }
    public Boolean isExist(String username){
        if(getSize()==0){
            return false;
        }
        Object temp = redisTemplate.opsForList().index(queueName, 0);
        return temp.toString().equals(username);
    }

    public synchronized String exitQueue() {
        Object o = redisTemplate.opsForList().rightPop(queueName);
        return (String) o;
    }

    public synchronized int cancelQueue(String username){
        Long result = redisTemplate.opsForList().remove(queueName, 0, username);
        return result.intValue();
    }
    public int getSize(){
        return redisTemplate.opsForList().size(queueName).intValue();
    }
}
