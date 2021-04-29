package com.wzxlq.entity;

import lombok.Data;

/**
 * @Package: com.wzxlq.entity
 * @ClassName: Answer
 * @Author: 李倩
 * @CreateTime: 2021/1/12 16:53
 * @Description:
 */
@Data
public class Answer {
    private String username;
    private Boolean answer;
    private String opponentName;
}