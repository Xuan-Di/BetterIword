package com.wzxlq.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author 王照轩
 */
@Component
@Slf4j
@ServerEndpoint(value = "/websocket/{username}")
//, configurator = MySpringConfigurator.class这个地方经验证不需用加上否则多设备连接回发现两台以上设备连接 回造成下面的session变为同一个，造成其他设备推送失败，所以不要盲目复制别人的，要注意此处
public class WebSocket {
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        clients.put(username, this);
        log.info(username+" 建立连接");
        System.out.println("已连接数量:" + getOnlineCount());
    }
    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        System.out.println("已连接数量:" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        // DataWrapper res = new DataWrapper();
        JSONObject req = JSONObject.parseObject(message);
        Integer code = req.getInteger("code");
        String msg = req.getString("msg");
        log.info("msg:{}", msg);
        log.info("username:{}", username);
        // 发送数据给服务端
        // sendMessageAll(JSON.toJSONString(res));
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        for (com.wzxlq.utils.WebSocket item : clients.values()) {
            if (item.username.equals(To)) {
                item.session.getAsyncRemote().sendText(message);
            }
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (com.wzxlq.utils.WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return clients.size();
    }

    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }
}