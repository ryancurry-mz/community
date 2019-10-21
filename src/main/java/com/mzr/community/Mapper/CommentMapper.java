package com.mzr.community.mapper;

import com.mzr.community.model.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into `comment` values(default,#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    void insert(Comment comment);

    @Select("select * from `comment` where parent_id=#{parentId}")
    List<Comment> commentList(Integer parentId);

    @Delete("delete from `comment` where parent_Id=#{parentId}")
    void delComment(Integer id);
}
