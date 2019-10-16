package com.mzr.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzr.community.mapper.QuestionMapper;
import com.mzr.community.mapper.UserMapper;
import com.mzr.community.model.Question;
import com.mzr.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/")
    public String index(HttpServletRequest req, Model model,
                        @RequestParam(value="pageNum",defaultValue = "1")Integer pageNum){
        Cookie[] cookies = req.getCookies();
        if(cookies != null && cookies.length!=0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        req.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        //引入分页插件
        PageHelper.startPage(pageNum,8);
        List<Question> list = questionMapper.list();
        PageInfo<Question> page = new PageInfo<>(list,8);
        model.addAttribute("questions",page);
        return "index";
    }
}
