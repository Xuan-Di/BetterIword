package com.wzxlq.controller;

import com.wzxlq.utils.TranslateApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 王照轩
 * @date 2020/4/14 - 15:07
 */
@RestController
public class TranslateWord {
   @GetMapping("translate")
   public String translate(String word){
      if(word==null||word.isEmpty()){
         return "";
      }
      String result=null;
      try {
          result = TranslateApi.getTranlate(word);
      } catch (IOException e) {
         e.printStackTrace();
      }
      return result;
   }
}
