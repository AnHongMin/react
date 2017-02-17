package com.mpc.post.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mpc.post.service.PostDto;
import com.mpc.post.service.PostService;
import com.mpc.post.service.PostVo;
import com.mpc.util.DispatchAction;
import com.mpc.util.json.JSONObject;
import com.mpc.util.json.JSONUtil;

@Controller
public class PostController extends DispatchAction {
	@Resource(name="postImpl")
	PostService postImpl;
	
	
	
	@RequestMapping(value="/post.do", params="method=getList")	
	public void getList(@ModelAttribute("vo")PostVo vo, HttpServletRequest req, HttpServletResponse res) throws Exception {		
		ajaxResponseJson(req, res, JSONUtil.toJSON(postImpl.getList()));		
	}
	
	@RequestMapping(value="/post.do", params="method=getPost")	
	public void getPost(@ModelAttribute("dto")PostDto dto, HttpServletRequest req, HttpServletResponse res) throws Exception {		
		ajaxResponseJson(req, res, JSONUtil.toJSON(postImpl.getPost(dto)));		
	}
	
	@RequestMapping(value="/post.do", params="method=insertPost")	
	public void insertPost(@ModelAttribute("dto")PostDto dto, HttpServletRequest req, HttpServletResponse res) throws Exception {
		JSONObject node = new JSONObject();
		postImpl.insertPost(dto);
		node.put("success", true);
		ajaxResponseJson(req, res, JSONUtil.toJSON(node));		
	}
	
	@RequestMapping(value="/post.do", params="method=updatePost")	
	public void updatePost(@ModelAttribute("dto")PostDto dto, HttpServletRequest req, HttpServletResponse res) throws Exception {
		JSONObject node = new JSONObject();
		postImpl.updatePost(dto);
		node.put("success", true);
		ajaxResponseJson(req, res, JSONUtil.toJSON(node));		
	}
	
	@RequestMapping(value="/post.do", params="method=deletePost")	
	public void deletePost(@ModelAttribute("dto")PostDto dto, HttpServletRequest req, HttpServletResponse res) throws Exception {
		JSONObject node = new JSONObject();
		boolean success = postImpl.deletePost(dto);
		node.put("success", success);
		ajaxResponseJson(req, res, JSONUtil.toJSON(node));		
	}

}
