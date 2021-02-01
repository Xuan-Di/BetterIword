package com.wzxlq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 王照轩
 * @date 2020/4/19 - 16:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWordDTO implements Serializable {

    /* private Integer wordId; */

    private String englishWord;

    private String pa;

    private String chineseWord;

    private String engInstance1;

    private String chiInstance1;

    private String engInstance2;

    private String chiInstance2;

    private Integer collect;

    private String pron;

//    private String openId;


}
