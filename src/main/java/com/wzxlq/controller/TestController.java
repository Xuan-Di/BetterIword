package com.wzxlq.controller;

import com.google.gson.Gson;
import com.wzxlq.entity.Nearbyperson;
import com.wzxlq.entity.Word;
import com.wzxlq.exception.QueryWordException;
import com.wzxlq.utils.WebSocket;
import com.wzxlq.utils.sentUtil;
import com.wzxlq.vo.QueryAllVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

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

    @GetMapping("getip")
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
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
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }
}
