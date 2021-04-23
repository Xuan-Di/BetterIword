package com.wzxlq.controller;

import com.wzxlq.service.MyRecord;
import com.wzxlq.utils.MP3ConvertPCM;
import com.wzxlq.utils.Sample;
import com.wzxlq.utils.TranslateApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 王照轩
 * @date 2020/4/14 - 15:07
 */
@RestController
public class TranslateWord {
    @Resource(name = "myRecord")
    private MyRecord mr;

    @GetMapping("voiceBegin")
    public String voiceBegin() {
        System.out.println("hhah");
        mr.capture();
        return "开始录音";
    }

    @ResponseBody
    @RequestMapping(value = "voiceEnd", produces = "text/html;charset=UTF-8")
    public String voiceEnd(HttpServletRequest request) {
        String word = null;
        //关闭录音
        mr.setStopflag(true);
        //获得项目下的路径（到webapp的位置）
        //String basepath = request.getSession().getServletContext().getRealPath("");
        String basepath = "I:/voice";
        String mp3 = "I:/voice/mp3/";
        //保存mp3文件
        String mp3path = mr.save(mp3);
        String pcmpath = basepath + "/pcm/a1.pcm";
        try {
            System.out.println(mp3path);
            //将mp3文件转换成pcm文件，并且保存在项目中
            MP3ConvertPCM.mp3Convertpcm(mp3path, pcmpath);
            word = Sample.beginSample(pcmpath);
            word = word.substring(0, word.length() - 1);
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return word;
    }

    @GetMapping("translate")
    public String translate(String word) {
        if (word == null || word.isEmpty()) {
            return "";
        }
        String result = null;
        try {
            result = TranslateApi.getTranlate(word);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
