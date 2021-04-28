package com.wzxlq.controller;

import com.wzxlq.entity.RankInfo;
import com.wzxlq.entity.User;
import com.wzxlq.service.StudyInfoService;
import com.wzxlq.service.UserService;
import com.wzxlq.service.impl.RankService;
import com.wzxlq.utils.GetContinuousSignInDay;
import com.wzxlq.vo.RankPersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author 李倩
 * @date 2021/4/16 - 21:47
 * 排行榜以及查询个人页面信息的接口
 */
@RestController
public class RankController {
    @Autowired
    private RankService rankService;
    @Autowired
    private UserService userService;
    @Autowired
    public RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StudyInfoService studyInfoService;
    /**
     * 功能描述:查询所有人的排名
     * @param
     * @return java.util.List<com.wzxlq.vo.RankPersonVO>
     */
    @GetMapping("queryRank")
    public List<RankPersonVO> queryRank() {
        ArrayList<RankPersonVO> list = new ArrayList<>();
        //按分数降序查询所有排名
        Set<ZSetOperations.TypedTuple<String>> set = rankService.reverseRangeWithScore("score", 0, -1);
        //根据openid查询User,以获得头像等信息,再返回给前端
        for (ZSetOperations.TypedTuple<String> tuple : set) {
            String openId = tuple.getValue();
            User user = userService.queryById(openId);
            list.add(new RankPersonVO(user.getNickName(), user.getHeadImgUrl(), tuple.getScore()));
        }
        return list;
    }
    /**
     * 功能描述 :查询个人设置页面的信息 包括:排名,分数,每天背多少单词,是否提醒,连续签到天数
     * @param request
     * @return com.wzxlq.entity.RankInfo
     */
    @GetMapping("queryMyRank")
    public RankInfo queryMyRank(HttpServletRequest request) {
        String openId = request.getHeader("token");
        //根据openid查询排名
        Long rank = rankService.rank("score", openId);
        //根据openid查询分数
        int score = rankService.queryScore("score", openId);
        Integer dailyCount = (Integer) redisTemplate.opsForHash().get("User_" + openId, "dailyCount");
        Integer isTixing = (Integer) redisTemplate.opsForHash().get("User_" + openId, "isTixing");
        dailyCount = dailyCount == null ? 20 : dailyCount;
        isTixing = isTixing == null ? 1 : isTixing;
        //根据openid查询签到的日期数组
        List<LocalDate> localDates = studyInfoService.querySignTime(openId);
        //反转数组
        Collections.reverse(localDates);
        //计算连续签到天数
        int linkSignInCount = GetContinuousSignInDay.getContinuousSignInDay(localDates);
        return new RankInfo(rank + 1, score, dailyCount, isTixing, linkSignInCount);
    }
}

