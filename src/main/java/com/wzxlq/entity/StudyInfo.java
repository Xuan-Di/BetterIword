package com.wzxlq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class StudyInfo implements Serializable {
    private static final long serialVersionUID = -29492830706248252L;
    
    private Integer id;
    
    private String openId;
    
    private Integer mark;
    
    private LocalDate createDate;
    
    private Integer wordCount;
    
    private Integer knowWord;
    
    private Integer unknowWord;
    
    private Integer fuzzyWord;

    public StudyInfo(String openId, Integer mark, LocalDate createDate, Integer wordCount, Integer knowWord, Integer unknowWord, Integer fuzzyWord) {
        this.openId = openId;
        this.mark = mark;
        this.createDate = createDate;
        this.wordCount = wordCount;
        this.knowWord = knowWord;
        this.unknowWord = unknowWord;
        this.fuzzyWord = fuzzyWord;
    }

    public StudyInfo(String openId, LocalDate createDate, Integer wordCount, Integer knowWord, Integer unknowWord, Integer fuzzyWord) {
        this.openId = openId;
        this.createDate = createDate;
        this.wordCount = wordCount;
        this.knowWord = knowWord;
        this.unknowWord = unknowWord;
        this.fuzzyWord = fuzzyWord;
    }
}