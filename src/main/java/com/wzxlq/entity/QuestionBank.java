package com.wzxlq.entity;

import java.io.Serializable;

/**
 * (QuestionBank)实体类
 *
 * @author makejava
 * @since 2021-01-30 12:53:41
 */
public class QuestionBank implements Serializable {

    private static final long serialVersionUID = -46682964908208583L;

            
    private String englishWord;

            
    private String queone;

            
    private String quetow;

            
    private String quethree;

            
    private String quefour;

            
    private String realque;

        
    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }
        
    public String getQueone() {
        return queone;
    }

    public void setQueone(String queone) {
        this.queone = queone;
    }
        
    public String getQuetow() {
        return quetow;
    }

    public void setQuetow(String quetow) {
        this.quetow = quetow;
    }
        
    public String getQuethree() {
        return quethree;
    }

    public void setQuethree(String quethree) {
        this.quethree = quethree;
    }
        
    public String getQuefour() {
        return quefour;
    }

    public void setQuefour(String quefour) {
        this.quefour = quefour;
    }
        
    public String getRealque() {
        return realque;
    }

    public void setRealque(String realque) {
        this.realque = realque;
    }

}