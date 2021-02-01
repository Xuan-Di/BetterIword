package com.wzxlq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author 王照轩
 * @date 2020/4/17 - 11:24
 * 0236980
 */
@Service
public class RankService {
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 添加一个元素, zset与set最大的区别就是每个元素都有一个score，因此有个排序的辅助功能;  zadd
     *
     * @param key
     * @param value
     * @param score
     */
    public void add(String key, String value, double score){
        redisTemplate.opsForZSet().add(key, value, score);
    }
    public int queryScore(String key,String value){
        Double score = redisTemplate.opsForZSet().score(key, value);
        if(score==null){
            return 0;
        }
        return score.intValue();
    }
    /**
     * 判断value在zset中的排名  zrank
     * <p>
     * 积分小的在前面
     *
     * @param key
     * @param value
     * @return
     */
    public Long rank(String key, String value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * score的增加or减少 zincrby
     *
     * @param key
     * @param value
     * @param score
     */
    public Double incrScore(String key, String value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    public Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScore(String key, int start, int end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }
}
