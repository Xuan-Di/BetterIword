package com.wzxlq.vo;

import com.wzxlq.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 王照轩
 * @date 2020/4/15 - 17:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodayWordVO {
   String openId;
   List<Word> wordList;
}
