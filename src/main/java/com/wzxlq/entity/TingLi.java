package com.wzxlq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 王照轩
 * @date 2020/6/2 - 12:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TingLi implements Serializable {
    private String title;
    private String  voice;
    private ArrayList<String> content;
}
