package com.wzxlq.utils;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.util.Hashtable;
import java.util.Map;

@ServerEndpoint("/send/{key}")
@Component
public class ServerSocket {

    private static final Map<String, Session> connections = new Hashtable<>();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    /***
     * 打开连接
     */
    @OnOpen
    public void onOpen(@PathParam("key") String id, Session session) {
        System.out.println(id + "连上了");
        connections.put(id, session);
    }

    /**
     * 接收消息
     */
    @OnMessage
    public void onMessage(@PathParam("key") String id, InputStream inputStream) throws IOException {
        int available = inputStream.available();
        if (available == 3) {
            System.out.println("录音结束");
            BufferedOutputStream bos = null;
            FileOutputStream fos = null;
            File file;
            try {
                file = new File("C:\\voice\\" + "wzx" + ".wav");

                //输出流
                fos = new FileOutputStream(file);

                //缓冲流
                bos = new BufferedOutputStream(fos);

                //将字节数组写出
                bos.write(byteArrayOutputStream.toByteArray());

            } catch (Exception e) {
                sendMessageTo("网络延迟,录音失败", id);
                e.printStackTrace();
            } finally {

                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                byteArrayOutputStream.reset();
            }
            Map<String, String> aipSpeech = BaiduUtil.getAipSpeech("C:\\voice\\" + "wzx" + ".wav");
            String result = aipSpeech.get("result");
            result = result.substring(0, result.length() - 1);
            sendMessageTo(result, id);
            System.out.println(result);
            return;
        }
        try {
            int rc = 0;
            byte[] buff = new byte[100];
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                byteArrayOutputStream.write(buff, 0, rc);
            }
        } catch (Exception e) {
            sendMessageTo("网络延迟,录音失败", id);
            e.printStackTrace();
        }
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        connections.get(To).getBasicRemote().sendText(message);
    }

    /**
     * 异常处理
     *
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        //TODO 日志打印异常
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(@PathParam("key") String id) throws IOException {
        System.out.println(id + "断开");
        connections.remove(id);
        byteArrayOutputStream.close();
    }
}
