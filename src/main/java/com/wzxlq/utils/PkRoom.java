package com.wzxlq.utils;

import com.wzxlq.entity.QuestionBank;
import com.wzxlq.entity.User;
import com.wzxlq.entity.Word;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.wzxlq.util
 * @ClassName: pkRoom
 * @Author: 王照轩
 * @CreateTime: 2021/1/11 15:31
 * @Description:
 */
@Data
public class PkRoom implements Serializable {
    private User opponentUser;
    private List<QuestionBank> words;
}