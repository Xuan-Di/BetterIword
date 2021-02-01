package com.wzxlq.service;

import com.wzxlq.entity.QuestionBank;
import java.util.List;

/**
 * (QuestionBank)表服务接口
 *
 * @author makejava
 * @since 2021-01-30 12:53:41
 */
public interface QuestionBankService {

    List<QuestionBank> queryAllByLimit();


}