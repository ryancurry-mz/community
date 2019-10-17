package com.mzr.community.service;

import com.mzr.community.mapper.QuestionMapper;
import com.mzr.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public void createOrupdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            //修改
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }

    public Question question(Integer id) {
        Question question = questionMapper.question(id);
        return question;
    }
}
