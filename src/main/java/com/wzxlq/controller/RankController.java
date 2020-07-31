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
 * @author 王照轩
 * @date 2020/4/16 - 21:47
 */
@RestController
public class RankController {
    @Autowired
    private RankService rankService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StudyInfoService studyInfoService;
    @GetMapping("queryRank")
    public List<RankPersonVO> queryRank() {
        ArrayList<RankPersonVO> list = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<String>> set = rankService.reverseRangeWithScore("score", 0, -1);
        for (ZSetOperations.TypedTuple<String> tuple : set) {
            String openId = tuple.getValue();
            User user = userService.queryById(openId);
            list.add(new RankPersonVO(user.getNickName(), user.getHeadImgUrl(), tuple.getScore()));
        }
        return list;
    }

    @GetMapping("queryMyRank")
    public RankInfo queryMyRank(HttpServletRequest request) {
        String openId = request.getHeader("token");
        Long rank = rankService.rank("score", openId);
        int score = rankService.queryScore("score", openId);
        Integer dailyCount = (Integer) redisTemplate.opsForHash().get("User_" + openId, "dailyCount");
        Integer isTixing = (Integer) redisTemplate.opsForHash().get("User_" + openId, "isTixing");
        dailyCount = dailyCount == null ? 20 : dailyCount;
        isTixing = isTixing == null ? 1 : isTixing;
        List<LocalDate> localDates = studyInfoService.querySignTime(openId);
        Collections.reverse(localDates);
        int linkSignInCount = GetContinuousSignInDay.getContinuousSignInDay(localDates);
        return new RankInfo(rank + 1, score, dailyCount, isTixing,linkSignInCount);
    }
}

