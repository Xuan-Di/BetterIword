package com.wzxlq.scheduler;

import com.sun.org.apache.regexp.internal.RE;
import com.wzxlq.entity.User;
import com.wzxlq.entity.mybean;
import com.wzxlq.service.UserService;
import com.wzxlq.service.impl.WordServiceImpl;
import com.wzxlq.utils.sentUtil;
import javafx.scene.layout.CornerRadii;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * @author 王照轩
 * @date 2020/4/13 - 23:22
 */
@Component
public class KillWordMap {
    @Autowired
    private WordServiceImpl wordService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    //    @Scheduled(cron = "0/5 * * * * ?")
    //5min
    //@Scheduled(cron = "0 0/5 * * * ?")
    @Scheduled(cron = "0 58 23 * * ?")
    public void killWordMap() {
        System.out.println("kill todayWordMap!!!");
        wordService.analyzeStudyInfo();
        wordService.killWordMap();
    }

    @Scheduled(cron = "0 30 20 * * ?")
    public void tixing() {
        List<User> users = userService.queryAll();
        String at = sentUtil.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + at;
        mybean mybean = new mybean();
        for (User user : users) {
            System.out.println(user.getOpenId());
            Integer isTixing = (Integer) redisTemplate.opsForHash().get("User_" + user.getOpenId(), "isTixing");
            Integer dailyCount = (Integer) redisTemplate.opsForHash().get("User_" + user.getOpenId(), "dailyCount");
            dailyCount = dailyCount == null ? 20 : dailyCount;
            Long knowNum = redisTemplate.opsForSet().size(user.getOpenId() + "know");
            int temp = dailyCount- knowNum.intValue();
            if (isTixing != null && isTixing == 1&&temp>0) {
                mybean.setTouser(user.getOpenId());
                mybean.setTemplate_id("-Yzcf1SMalK3DcAA7P5yw5eiHigMdBATq-BwdizREYw");
                com.wzxlq.entity.mybean.DataBean dataBean = new mybean.DataBean();
                com.wzxlq.entity.mybean.DataBean.FirstBean firstBean = new com.wzxlq.entity.mybean.DataBean.FirstBean();
                firstBean.setValue("背单词提醒!\n");
                firstBean.setColor("#abcdef");
                dataBean.setFirst(firstBean);
                com.wzxlq.entity.mybean.DataBean.CompanyBean companyBean = new com.wzxlq.entity.mybean.DataBean.CompanyBean();
                companyBean.setColor("#fff000");
                companyBean.setValue("我爱背单词\n");
                dataBean.setCompany(companyBean);
                com.wzxlq.entity.mybean.DataBean.TimeBean timeBean = new com.wzxlq.entity.mybean.DataBean.TimeBean();
                timeBean.setColor("#1f1f1f");
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
                String strDate2 = dtf2.format(ldt);
                timeBean.setValue(strDate2 + "\n");
                dataBean.setTime(timeBean);
                com.wzxlq.entity.mybean.DataBean.ResultBean resultBean = new com.wzxlq.entity.mybean.DataBean.ResultBean();
                resultBean.setColor("#173177");
                resultBean.setValue(user.getNickName() + ",你有" + temp + "个单词未背\n");
                dataBean.setResult(resultBean);
                com.wzxlq.entity.mybean.DataBean.RemarkBean remarkBean = new com.wzxlq.entity.mybean.DataBean.RemarkBean();
                remarkBean.setColor("#173177");
                remarkBean.setValue("只要不失去方向，就不会失去自我。");
                dataBean.setRemark(remarkBean);
                mybean.setData(dataBean);
                JSONObject json1 = JSONObject.fromObject(mybean);
                String result1 = sentUtil.post(url, json1.toString());
                System.out.println(result1);
            }
        }
    }
}
