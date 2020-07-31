package com.wzxlq.vo;

import com.wzxlq.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王照轩
 * @date 2020/4/14 - 16:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastWordVO implements Serializable {
    String openId;
    List<Word> wordlist ;
}
