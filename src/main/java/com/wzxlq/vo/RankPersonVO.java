package com.wzxlq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李倩
 * @date 2021/4/16 - 21:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankPersonVO
{
    String nickName;
    String headImageUrl;
    Double score;
}
