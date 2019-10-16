package com.mzr.community.mapper;

import com.mzr.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question values(default,#{title},#{details},#{gmtCreate},#{gmtModified},#{creator},default,default,default,#{tag})")
    void create(Question question);

    @Select({"select question.*,`user`.avatar_url from question,`user` where question.creator = `user`.id"})
    List<Question> list();
}