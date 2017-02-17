package com.mpc.post.service;

import java.util.ArrayList;

public interface PostService {
	public ArrayList<PostDto> getList() throws Exception;
	
	public PostDto getPost(PostDto dto) throws Exception;
	
	public void insertPost(PostDto dto) throws Exception;
	
	public void updatePost(PostDto dto) throws Exception;
	
	public boolean deletePost(PostDto dto) throws Exception;
}
