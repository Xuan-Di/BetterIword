package com.wzxlq.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wzxlq.entity.AccessToken;
import com.wzxlq.entity.JsapiTicket;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

/**
 * @author 王照轩
 * @date 2020/2/29 - 10:51
 */
public class sentUtil {
    //微信公众号
    private static final String APPID = "wxb1b153e1c472f2de";
    private static final String APPSECRET = "2a7de949357073700a095c4b86b4c290";

    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static AccessToken at = null;
    private static JsapiTicket jt = null;

    public static String post(String url, String data) {
        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            // 要发送数据出去，必须要设置为可发送数据状态
            connection.setDoOutput(true);
            // 获取输出流
            OutputStream os = connection.getOutputStream();
            // 写出数据
            os.write(data.getBytes());
            os.close();
            // 获取输入流
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向指定的地址发送get请求
     */
    public static String get(String url) {
        try {
            URL urlObj = new URL(url);
            // 开连接
            URLConnection connection = urlObj.openConnection();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取token
     */

    private static void getToken() {
        String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        String tokenStr = sentUtil.get(url);
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(tokenStr);
        JsonObject json = root.getAsJsonObject();
        String token = json.get("access_token").getAsString();
        String expiresIn = json.get("expires_in").getAsString();
        System.out.println("1111"+token);
        //创建token对象,并存起来。
        sentUtil.at = new AccessToken(token, expiresIn);
    }

    /**
     * 向处暴露的获取token的方法
     */
    public static String getAccessToken() {
        if (at == null || at.isExpired()) {
            getToken();
        }
        return at.getAccessToken();
    }
    public static String getJsapiTicket(){
        if(jt==null||jt.isExpired()){
            getTicket();
        }
        return jt.getTicket();
    }
    private static void getTicket(){
        String URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        URL.replace("ACCESS_TOKEN", sentUtil.getAccessToken());
        String result = sentUtil.get(URL);
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(result);
        JsonObject json = root.getAsJsonObject();
        System.out.println(json);
        String token = json.get("ticket").getAsString();
        String expiresIn = json.get("expires_in").getAsString();
        sentUtil.jt=new JsapiTicket(token,expiresIn);
    }


 }
