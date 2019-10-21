package com.mzr.community.controller;

import com.mzr.community.mapper.CommentMapper;
import com.mzr.community.mapper.QuestionMapper;
import com.mzr.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping("/comment")
    public String post(@RequestParam String content,
                       @RequestParam Integer commentator,
                       @RequestParam Long question_id){
        Comment comment = new Comment();
        comment.setType(1);
        comment.setParentId(question_id);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(commentator);
        comment.setContent(content);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        int id = Integer.parseInt(String.valueOf(question_id));
        questionMapper.inComment(id);
        return "redirect:/question/" + question_id;
    }
}


