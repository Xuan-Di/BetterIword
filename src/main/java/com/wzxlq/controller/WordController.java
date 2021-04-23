package com.wzxlq.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wzxlq.dto.WordInfoDTO;
import com.wzxlq.entity.*;
import com.wzxlq.exception.QueryWordException;
import com.wzxlq.service.UserService;
import com.wzxlq.service.WordService;
import com.wzxlq.utils.GeoHashUtil;
import com.wzxlq.utils.sentUtil;
import com.wzxlq.vo.QueryAllVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.geo.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * (Word)表控制层
 *
 * @author makejava
 * @since 2020-04-13 21:49:20
 */
@Slf4j
@RestController
//@RequestMapping("word")
public class WordController {
    /**
     * 服务对象
     */
    @Resource
    private WordService wordService;

    @Resource
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private GeoHashUtil geoHashUtil;

    @GetMapping("queryAll")
    public QueryAllVO queryAll(HttpServletRequest request) {
        //获取code
        String code = request.getParameter("code");
        //换取accesstoken的地址
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APPID", "wxb1b153e1c472f2de")
                .replace("SECRET", "2a7de949357073700a095c4b86b4c290")
                .replace("CODE", code);
        String result = sentUtil.get(url);
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(result);
        JsonObject json = root.getAsJsonObject();
        String at = json.get("access_token").getAsString();
        String openId = json.get("openid").getAsString();
        //拉取用户的基本信息
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", at).replace("OPENID", openId);
        result = sentUtil.get(url);
        Gson gson = new Gson();
        wxUser wxUser = gson.fromJson(result, wxUser.class);
        System.out.println(wxUser.getHeadimgurl());
        System.out.println(result);
        User user = userService.queryById(openId);
        String ip = "";
        ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        if (!"127.0.0.1".equals(ip)) {
            String json1 = sentUtil.get("http://api.map.baidu.com/location/ip?ak=ryFnGbYBMuoymNnla3TSxxpE7rCrPNfT&ip=" + ip + "&coor=bd09ll");
            Nearbyperson nearbyperson = gson.fromJson(json1, Nearbyperson.class);
            geoHashUtil.redisGeoAdd("location", Double.parseDouble(nearbyperson.getContent().getPoint().getX()),
                    Double.parseDouble(nearbyperson.getContent().getPoint().getY()), openId);
        }

        if (user == null) {
            List<Word> words = wordService.firstQueryWords(openId);
            if (words == null || words.isEmpty()) {
                log.error("第一次查询不到单词");
                throw new QueryWordException("查不到单词", 500);
            }
            //用户第一次进入，插入用户信息到数据库
            userService.insert(new User(openId, wxUser.getNickname(), 1, wxUser.getHeadimgurl()));
            //默认用户设置每日提醒背单词
            redisTemplate.opsForHash().put("User_" + openId, "isTixing", 1);
            redisTemplate.opsForHash().put("User_" + openId, "dailyCount", 20);
            redisTemplate.opsForHash().put("User_" + openId, "wordIndex", 20);
            return new QueryAllVO(openId, words);
        } else {
            List<Word> words = wordService.queryTodayWords(openId);
            return new QueryAllVO(openId, words);
        }
    }

    @GetMapping("queryWord")
    public List<Word> queryWord() {
        return wordService.queryAllByLimit(1080, 10);
    }

    @GetMapping("queryInTest")
    public QueryAllVO queryInTest(String openId, HttpServletRequest request) {
        String ip = "";
        ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        User user = userService.queryById(openId);
        List<Word> words = null;
        if (user == null) {
            words = wordService.firstQueryWords(openId);
            if (words == null || words.isEmpty()) {
                log.error("第一次查询不到单词");
                throw new QueryWordException("查不到单词", 500);
            }
            userService.insert(new User(openId, openId + "测", 1, "http://wework.qpic.cn/bizmail/bia8Sib0kGlNZbysx3LwoGou727nT1ibdY12POO95bXoIU9SibcrffqFjg/0"));
            //默认用户设置每日提醒背单词和每日背20个单词
            redisTemplate.opsForHash().put("User_" + openId, "isTixing", 1);
            redisTemplate.opsForHash().put("User_" + openId, "dailyCount", 20);
            redisTemplate.opsForHash().put("User_" + openId, "wordIndex", 20);
        } else {
            words = wordService.queryTodayWords(openId);
        }

        if (!"127.0.0.1".equals(ip)) {
            String json = sentUtil.get("http://api.map.baidu.com/location/ip?ak=ryFnGbYBMuoymNnla3TSxxpE7rCrPNfT&ip=" + ip + "&coor=bd09ll");
            Gson gson = new Gson();
            Nearbyperson nearbyperson = gson.fromJson(json, Nearbyperson.class);
            geoHashUtil.redisGeoAdd("location", Double.parseDouble(nearbyperson.getContent().getPoint().getX()),
                    Double.parseDouble(nearbyperson.getContent().getPoint().getY()), openId);
        }
            return new QueryAllVO(openId, words);
        }


        /**
         * @param keyword
         * @return
         */
        @GetMapping("/word/queryInEs")
        public List<Word> queryInEs (String keyword){
            return wordService.queryByFuzzyMatching(keyword);
            //return wordService.queryInEs(keyword);
        }

        /**
         * 功能描述 :每次点击都要经过这个统计
         *
         * @param wordInfoDTO
         * @param request
         * @return boolean
         */
        @PostMapping("/word/wordInfo")
        public boolean wordInfo (@RequestBody WordInfoDTO wordInfoDTO, HttpServletRequest request){
            String openId = request.getHeader("token");
            return wordService.wordInfo(wordInfoDTO, openId);
        }

        /**
         * 功能描述:完成今天的背单词任务
         *
         * @param request
         * @return boolean
         */

        @GetMapping("/word/finishToday")
        public boolean finishToday (HttpServletRequest request){
            String openId = request.getHeader("token");
            return wordService.killWordMapWithOpenId(openId);
        }


        /**
         * 功能描述 :复习
         *
         * @param request
         * @return java.util.List<com.wzxlq.entity.Word>
         */
        @GetMapping("/word/review")
        public List<Word> review (HttpServletRequest request){
            String openId = request.getHeader("token");
            return wordService.review(openId);
        }

        /**
         * 功能描述: 词汇量测试
         *
         * @param
         * @return java.util.List<com.wzxlq.entity.Word>
         */
        @GetMapping("/word/wordCountTest")
        public List<Word> wordCountTest () {
            return wordService.wordCountTest();
        }
    }