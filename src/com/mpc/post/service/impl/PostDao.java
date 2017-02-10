package com.mpc.post.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.mpc.post.service.PostDto;

@Component
public class PostDao {

	@Resource(name = "commonSqlSession")
	private SqlSession commonSqlSession;

	public List<Object> getList() throws SQLException {
		return commonSqlSession.selectList("Post.getList", null);
	}
	
	public void insertPost(PostDto dto) throws SQLException {
		commonSqlSession.insert("Post.insertPost", dto);
	}
	
	public void updatePost(PostDto dto) throws SQLException {
		commonSqlSession.update("Post.updatePost", dto);
	}
	
	public int deletePost(PostDto dto) throws SQLException {
		return commonSqlSession.delete("Post.deletePost", dto);
	}
}
