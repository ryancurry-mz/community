package com.mzr.community.controller;

import com.mzr.community.mapper.QuestionMapper;
import com.mzr.community.mapper.UserMapper;
import com.mzr.community.model.Question;
import com.mzr.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        Question question = questionMapper.question(id);
        User user = userMapper.findById(question.getCreator());
        question.setUser(user);
        questionMapper.inView(question.getId());
        model.addAttribute("question",question);
        return "question";
    }
}
