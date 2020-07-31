package com.wzxlq.controller;

import com.wzxlq.dto.UserWordDTO;
import com.wzxlq.entity.UserWord;
import com.wzxlq.service.UserWordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (UserWord)表控制层
 *
 * @author makejava
 * @since 2020-04-15 18:01:11
 */
@RestController
@RequestMapping("userWord")
public class UserWordController {
    /**
     * 服务对象
     */
    @Resource
    private UserWordService userWordService;

    @Autowired
    private RedisTemplate redisTemplate;
    //加入单词到生词本
    @PostMapping("insertWord")
    public UserWord insertWord(@RequestBody UserWordDTO userWordDTO, HttpServletRequest request) {
        String openId = request.getHeader("token");
        UserWord userWord = new UserWord();
        BeanUtils.copyProperties(userWordDTO, userWord);
        userWord.setOpenId(openId);
        UserWord uw = userWordService.insert(userWord);
        return uw;
    }

    //从生词本中删除单词
    @GetMapping("deleteWord")
    public Boolean deleteWord(String englishWord,HttpServletRequest request) {
        String openId = request.getHeader("token");
        boolean delete = userWordService.deleteById(englishWord,openId);
        return delete;
    }

    //查询并排序
    @GetMapping("queryAndSort")
    public List<UserWord> sort(Integer sort, HttpServletRequest request) {
        String openId = request.getHeader("token");
        if (sort == null) {
            sort = 1;
        }
        List<UserWord> userWordList = userWordService.queryAllByOpenId(openId);
        if (sort == 1) {
            userWordList.sort((a, b) -> a.getEnglishWord().compareTo(b.getEnglishWord()));
        }
        if (sort == 2) {
            userWordList.sort((a, b) -> b.getCollect() - a.getCollect());
        }
        return userWordList;
    }
}