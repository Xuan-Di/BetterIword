package com.wzxlq.entity;

import lombok.Data;

/**
 * @Package: com.wzxlq.entity
 * @ClassName: Answer
 * @Author: 王照轩
 * @CreateTime: 2021/1/12 16:53
 * @Description:
 */
@Data
public class Answer {
    private String username;
    private Boolean answer;
    private String opponentName;
}