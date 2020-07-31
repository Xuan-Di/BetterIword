package com.wzxlq.vo;

import com.wzxlq.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王照轩
 * @date 2020/5/13 - 21:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryAllVO implements Serializable {
     String token;
     List<Word> words;
}
