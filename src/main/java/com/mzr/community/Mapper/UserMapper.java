package com.mzr.community.mapper;

import com.mzr.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //插入用户
    @Insert("insert into user values(default,#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insUser(User user);

    //通过cookie的token查询用户是否登录，若登录获取用户信息
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id = #{id}")
    void update(User user);
}
