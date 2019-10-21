package com.mzr.community.controller;

import com.mzr.community.mapper.CommentMapper;
import com.mzr.community.mapper.QuestionMapper;
import com.mzr.community.mapper.UserMapper;
import com.mzr.community.model.Comment;
import com.mzr.community.model.Question;
import com.mzr.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model){
        Question question = questionMapper.question(id);
        User user = userMapper.findById(question.getCreator());
        question.setUser(user);
        questionMapper.inView(question.getId());
        List<Comment> comments = commentMapper.commentList(id);
        model.addAttribute("question",question);
        model.addAttribute("comments",comments);
        return "question";
    }

    @GetMapping("/delComment/{id}")
    public String delComment(@PathVariable(name = "id")Integer id){
        commentMapper.delComment(id);
        questionMapper.subComment(id);
        return "redirect:/question/" + id;
    }
}
