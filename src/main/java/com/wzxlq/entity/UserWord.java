package com.wzxlq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWord implements Serializable {
    private static final long serialVersionUID = -24490526047878749L;
    
    private Integer wordId;
    
    private String englishWord;
    
    private String pa;
    
    private String chineseWord;
    
    private String engInstance1;
    
    private String chiInstance1;
    
    private String engInstance2;
    
    private String chiInstance2;
    
    private Integer collect;
    
    private String pron;
    
    private String openId;


}