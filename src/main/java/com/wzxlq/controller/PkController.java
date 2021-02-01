package com.wzxlq.controller;

import com.alibaba.fastjson.JSON;
import com.wzxlq.dto.ResponseDto;
import com.wzxlq.entity.Answer;
import com.wzxlq.entity.QuestionBank;
import com.wzxlq.service.QuestionBankService;
import com.wzxlq.utils.MatchQueue;
import com.wzxlq.utils.PkRoom;
import com.wzxlq.utils.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Package: com.wzxlq.controller
 * @ClassName: PkController
 * @Author: 王照轩
 * @CreateTime: 2021/1/11 16:29
 * @Description:
 */
@RestController
@Slf4j
public class PkController {
    @Resource
    private MatchQueue matchQueue;
    @Resource
    private WebSocket webSocket;
    @Resource
    private QuestionBankService questionBankService;

    @GetMapping("/enterQueue/{username}")
    public void enterQueue(@PathVariable String username) {
        log.info(username+" 进入队列");
        if (matchQueue.getSize() >= 1) {
            String opponentName = matchQueue.exitQueue();
            log.info("对手的姓名:{}", opponentName);
            PkRoom firstPerspective = new PkRoom();
            PkRoom secondPerspective = new PkRoom();
            firstPerspective.setOpponentName(opponentName);
            secondPerspective.setOpponentName(username);
            List<QuestionBank> questions = questionBankService.queryAllByLimit();
            firstPerspective.setWords(questions);
            secondPerspective.setWords(questions);
            ResponseDto firstRes = new ResponseDto("match");
            ResponseDto secondRes = new ResponseDto("match");
            firstRes.setContent(firstPerspective);
            secondRes.setContent(secondPerspective);
            String firstStr = JSON.toJSONString(firstRes);
            String secondStr = JSON.toJSONString(secondRes);
            try {
                webSocket.sendMessageTo(firstStr, username);
                webSocket.sendMessageTo(secondStr, opponentName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            matchQueue.enterQueue(username);
        }
    }

    @GetMapping("/cancelQueue/{username}")
    public int cancelQueue(@PathVariable String username) {
        log.info(username+" 取消排队");
        return matchQueue.cancelQueue(username);
    }

    @PostMapping("/submitAnswer")
    public void submitAnswer(@RequestBody Answer answer) {
        ResponseDto responseDto = new ResponseDto("score");
        if (answer.getAnswer()) {
            responseDto.setContent(answer.getUsername() + " 回答正确");

        } else {
            responseDto.setContent(answer.getUsername() + " 回答错误");
        }
        String jsonStr = JSON.toJSONString(responseDto);
        try {
            webSocket.sendMessageTo(jsonStr, answer.getOpponentName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
