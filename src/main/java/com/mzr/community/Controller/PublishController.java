package com.mzr.community.controller;

import com.mzr.community.mapper.QuestionMapper;
import com.mzr.community.mapper.UserMapper;
import com.mzr.community.model.Question;
import com.mzr.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(
            @RequestParam("title")String title,
            @RequestParam("details")String details,
            @RequestParam("tag")String tag,
            HttpServletRequest req,
            Model model){

        User user = (User)req.getSession().getAttribute("user");

        if(user == null){
            model.addAttribute("error","用户未登录！");
            return "/publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDetails(details);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:/";
    }
}
