package com.mzr.community.mapper;

import com.mzr.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment values(id=#{id},parent_id=#{parentId},type=#{type},commentator=#{commentator},gmt_create=#{gmtCreate},gmt_modifed=#{gmtModified},like_count=#{likeCount},content=#{content})")
    void insert(Comment comment);
}
