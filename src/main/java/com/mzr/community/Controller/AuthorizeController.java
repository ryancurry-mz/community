package com.mzr.community.controller;

import com.mzr.community.Mapper.UserMapper;
import com.mzr.community.dto.AccessTokenDTO;
import com.mzr.community.dto.GithubUser;
import com.mzr.community.model.User;
import com.mzr.community.provider.GithubProvider;
import org.apache.ibatis.annotations.Mapper;
import org.omg.CORBA.ServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String authorize(@RequestParam(value = "code")String code,
                            @RequestParam(value = "state")String state,
                            HttpServletRequest req){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
//        System.out.println(user.getName() + " " + user.getId() + " " + user.getBio());
        if(githubUser != null){
            User user = new User();
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            System.out.println(user);
            userMapper.insUser(user);
            //登陆成功，写session和cookie
            req.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }
}
