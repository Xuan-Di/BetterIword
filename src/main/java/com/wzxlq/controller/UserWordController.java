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
 *  生词本接口
 * @author 李倩
 */
@RestController
@RequestMapping("userWord")
public class UserWordController {

    @Resource
    private UserWordService userWordService;

    //加入单词到生词本
    @PostMapping("insertWord")
    public UserWord insertWord(@RequestBody UserWordDTO userWordDTO, HttpServletRequest request) {
        String openId = request.getHeader("token");
        UserWord userWord = new UserWord();
        //将userWordDTO的属性全部赋值给userWord
        BeanUtils.copyProperties(userWordDTO, userWord);
        userWord.setOpenId(openId);
        return userWordService.insert(userWord);
    }

    //从生词本中删除单词
    @GetMapping("deleteWord")
    public Boolean deleteWord(String englishWord,HttpServletRequest request) {
        String openId = request.getHeader("token");
        return userWordService.deleteById(englishWord,openId);
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
            //按照字典序排序
            userWordList.sort((a, b) -> a.getEnglishWord().compareTo(b.getEnglishWord()));
        }
        if (sort == 2) {
            //按照词频排序
            userWordList.sort((a, b) -> b.getCollect() - a.getCollect());
        }
        return userWordList;
    }
}