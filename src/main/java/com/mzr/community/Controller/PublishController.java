package com.mzr.community.controller;

import com.mzr.community.mapper.QuestionMapper;
import com.mzr.community.mapper.UserMapper;
import com.mzr.community.model.Question;
import com.mzr.community.model.User;
import com.mzr.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Integer id,Model model){
        Question question = questionService.question(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("details",question.getDetails());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false)String title,
            @RequestParam(value = "details",required = false)String details,
            @RequestParam(value = "tag",required = false)String tag,
            @RequestParam(value = "id",required = false)Integer id,
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
        question.setId(id);

        questionService.createOrupdate(question);
        return "redirect:/";
    }
}
