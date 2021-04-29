package com.wzxlq.entity;

/**
 * @author 李倩
 */
public class JsapiTicket {

    private String ticket;
    private long expireTime;

    public String getTicket() {
        return ticket;
    }

    public JsapiTicket(String ticket, String expireIn) {
        super();
        this.ticket = ticket;
        expireTime = System.currentTimeMillis() + Integer.parseInt(expireIn) * 1000;
    }

    /**
     * 判断token是否过期
     *
     */
    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }
}
