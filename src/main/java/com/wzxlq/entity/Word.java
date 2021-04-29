package com.wzxlq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Word)实体类
 *
 * @author 李倩
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word implements Serializable {
    private static final long serialVersionUID = -30478980584121773L;
    
    private String englishWord;
    
    private String pa;
    
    private String chineseWord;
    
    private String engInstance1;
    
    private String chiInstance1;
    
    private String engInstance2;
    
    private String chiInstance2;
    
    private Integer collect;
    
    private String pron;

    public Word(String englishWord, Integer collect) {
        this.englishWord = englishWord;
        this.collect = collect;
    }
}