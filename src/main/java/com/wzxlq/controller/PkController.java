package com.wzxlq.controller;

import com.alibaba.fastjson.JSON;
import com.wzxlq.dto.ResponseDto;
import com.wzxlq.entity.Answer;
import com.wzxlq.entity.QuestionBank;
import com.wzxlq.entity.User;
import com.wzxlq.service.QuestionBankService;
import com.wzxlq.service.UserService;
import com.wzxlq.utils.MatchQueue;
import com.wzxlq.utils.PkRoom;
import com.wzxlq.utils.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.wzxlq.controller
 * @ClassName: PkController
 * @Author: 李倩
 * @CreateTime: 2021/1/11 16:29
 * @Description:
 * 在线单词pk接口
 */
@RestController
@Slf4j
public class PkController {
    /**
     * 引入pk排队队列
     */
    @Resource
    private MatchQueue matchQueue;
    @Resource
    private WebSocket webSocket;
    @Resource
    private QuestionBankService questionBankService;
    @Resource
    private UserService userService;

    /**
     * 功能描述:pk排队接口
     * @param username
     * @return com.wzxlq.dto.ResponseDto
     */
    @GetMapping("/enterQueue/{username}")
    public ResponseDto enterQueue(@PathVariable String username) {
        //初始化返回对象
        ResponseDto responseDto = new ResponseDto();
        //防止同一个人多次重复进入pk队列
        if(matchQueue.isExist(username)){
            log.info("重复进队列 无效！");
            responseDto.setCode("500");
            return responseDto;
        }
        //如果队列中有人
        if (matchQueue.getSize() >= 1) {
            //弹出队列中一个人,作为对手
            String opponentName = matchQueue.exitQueue();
            //初始化我视角
            PkRoom firstPerspective = new PkRoom();
            //初始化对手视角
            PkRoom secondPerspective = new PkRoom();
            //根据openid查询User对象,用以获得头像等信息
            User myself = userService.queryById(username);
            User opponent = userService.queryById(opponentName);
            log.info("对手的openid:{}", opponent.getOpenId());
            log.info("对手的姓名:{}", opponent.getNickName());
            firstPerspective.setOpponentUser(opponent);
            secondPerspective.setOpponentUser(myself);
            //获得双方pk时的单词题目
            List<QuestionBank> questions = questionBankService.queryAllByLimit();
            firstPerspective.setWords(questions);
            secondPerspective.setWords(questions);
            //传输信息初始化,并设置类型为match
            ResponseDto firstRes = new ResponseDto("match");
            ResponseDto secondRes = new ResponseDto("match");
            firstRes.setContent(firstPerspective);
            secondRes.setContent(secondPerspective);
            //将传输信息对象json序列化为字符串
            String firstStr = JSON.toJSONString(firstRes);
            String secondStr = JSON.toJSONString(secondRes);
            try {
                //给pk双方发送信息
                webSocket.sendMessageTo(firstStr, username);
                webSocket.sendMessageTo(secondStr, opponentName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //如果队列没人,那么自己入队
            matchQueue.enterQueue(username);
        }
        log.info("当前队列长度:"+ matchQueue.getSize());
        responseDto.setCode("200");
        return responseDto;
    }
    /**
     * 功能描述:取消排队接口
     * @param username
     * @return com.wzxlq.dto.ResponseDto
     */
    @GetMapping("/cancelQueue/{username}")
    public ResponseDto cancelQueue(@PathVariable String username) {
        ResponseDto responseDto = new ResponseDto();
        log.info(username+" 取消排队");
        Map<String, WebSocket> client = WebSocket.getClients();
        //从websocket中移除
        client.remove(username);
        System.out.println("已连接数量:" + WebSocket.getOnlineCount());
        //从排队队列中移除
        matchQueue.cancelQueue(username);
        responseDto.setCode("200");
        return responseDto;
    }
    /**
     * 功能描述 :提交答案接口
     * @param answer
     * @return void
     */
    @PostMapping("/submitAnswer")
    public void submitAnswer(@RequestBody Answer answer) {
        //初始化传输信息对象,并设置类型为score
        ResponseDto responseDto = new ResponseDto("score");
        //如果回答正确
        if (answer.getAnswer()) {
            log.info(answer.getUsername() + " 回答正确");
            responseDto.setContent(answer.getUsername() + " 回答正确");
        } else {
            log.info(answer.getUsername() + " 回答错误");
            responseDto.setContent(answer.getUsername() + " 回答错误");
        }
        //对象序列化
        String jsonStr = JSON.toJSONString(responseDto);
        try {
            //给对手发送我的答案
            webSocket.sendMessageTo(jsonStr, answer.getOpponentName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
