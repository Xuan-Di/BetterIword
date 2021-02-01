package com.wzxlq.service.impl;

import com.wzxlq.entity.QuestionBank;
import com.wzxlq.dao.QuestionBankDao;
import com.wzxlq.service.QuestionBankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (QuestionBank)表服务实现类
 *
 * @author makejava
 * @since 2021-01-30 12:53:41
 */
@Service("questionBankService")
public class QuestionBankServiceImpl implements QuestionBankService {
    @Resource
    private QuestionBankDao questionBankDao;


    @Override
    public List<QuestionBank> queryAllByLimit() {
        return this.questionBankDao.queryAllByLimit();
    }



}