package com.wzxlq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {

    private String accessToken;
    private long expireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public AccessToken(String accessToken, String expireIn) {
        super();
        this.accessToken = accessToken;
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
