package com.mzr.community.Mapper;

import com.mzr.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user values(default,#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insUser(User user);
}
