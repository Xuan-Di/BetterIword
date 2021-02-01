package com.wzxlq.dao;

import com.wzxlq.entity.QuestionBank;
import java.util.List;

/**
 * (QuestionBank)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-30 12:53:41
 */
public interface QuestionBankDao {
    


    List<QuestionBank> queryAllByLimit();


}