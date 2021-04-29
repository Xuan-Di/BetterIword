package com.wzxlq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
