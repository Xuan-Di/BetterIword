package com.wzxlq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王照轩
 * @date 2020/5/6 - 9:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankInfo {
    private Long rank;
    private int wordCount;
    private int dailyCount;
    private int isTixing;
    private int linkSignInCount;
}
