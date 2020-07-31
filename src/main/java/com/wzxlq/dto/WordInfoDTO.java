package com.wzxlq.dto;

import com.wzxlq.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 王照轩
 * @date 2020/4/28 - 15:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordInfoDTO implements Serializable {

    public Word word;
    public String type;

}
