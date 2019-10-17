package com.mzr.community.service;

import com.mzr.community.mapper.UserMapper;
import com.mzr.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrupdate(User user) {
        User dbuser = userMapper.findByAccountId(user.getAccountId());
        if(dbuser == null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insUser(user);
        }else{
            //更新
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setGmtModified(System.currentTimeMillis());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.update(dbuser);
        }
    }
}
