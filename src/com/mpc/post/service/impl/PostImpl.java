package com.mpc.post.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mpc.post.service.PostDto;
import com.mpc.post.service.PostService;

@Component
public class PostImpl implements PostService {
	@Resource(name = "postDao")
	private PostDao postDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<PostDto> getList() throws Exception {
		ArrayList<PostDto> data = new ArrayList<PostDto>();
		data = (ArrayList)postDao.getList();
		return data;
	}
	
	public void insertPost(PostDto dto) throws Exception {
		postDao.insertPost(dto);
	}

	public void updatePost(PostDto dto) throws Exception {
		postDao.updatePost(dto);
	}
	
	public boolean deletePost(PostDto dto) throws Exception {
		int result = postDao.deletePost(dto);
		return (result > 0)?true:false;
	}
}