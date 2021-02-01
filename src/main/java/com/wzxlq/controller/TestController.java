package com.wzxlq.controller;

import com.wzxlq.utils.WebSocket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Package: com.wzxlq.controller
 * @ClassName: TestController
 * @Author: 王照轩
 * @CreateTime: 2020/12/11 13:49
 * @Description:
 */
@RestController
@RequestMapping("business")
public class TestController {
    //@ApiOperation(value = "获取用户信息", notes = "username是唯一属性，单个用户可直接通过username获取") //标注在方法：用以备注接口描述
    @PostMapping(value = "/user/query")
    public String getUserInfo(@RequestBody String username) {
        WebSocket ws = new WebSocket();
        try {
            //ws.sendMessageAll(JSON.toJSONString(username));
            ws.sendMessageTo("你有新订单了", username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username;
    }
}
