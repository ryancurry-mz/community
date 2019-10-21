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

    @Select("select question.*,`user`.avatar_url from question,`user` where question.creator = `user`.id")
    List<Question> list();

    @Select("select * from question where id = #{id}")
    Question question(@Param("id") Integer id);

    @Update("update question set title=#{title},details=#{details},gmt_modified=#{gmtModified},tag=#{tag} where id = #{id}")
    void update(Question question);

    @Update("update question set view_count = view_count + 1 where id = #{id}")
    void inView(@Param("id") Integer id);

    @Update("update question set comment_count = comment_count + 1 where id = #{id}")
    void inComment(@Param("id") Integer id);

    @Update("update question set comment_count = comment_count - 1 where id = #{id}")
    void subComment(@Param("id") Integer id);
}