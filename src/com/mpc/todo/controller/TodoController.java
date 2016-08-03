package com.mpc.todo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mpc.todo.service.TodoDto;
import com.mpc.todo.service.TodoService;
import com.mpc.todo.service.TodoVo;
import com.mpc.util.DispatchAction;
import com.mpc.util.json.JSONObject;
import com.mpc.util.json.JSONUtil;

@Controller
public class TodoController extends DispatchAction {
	@Resource(name="todoImpl")
	TodoService todoImpl;
	
	/**
	 * 목록 Paging
	 * @param vo
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping(value="/todo.do", params="method=getTodoList")	
	public void getTodoList(@ModelAttribute("vo")TodoVo vo, HttpServletRequest req, HttpServletResponse res) throws Exception {
		String listBlock = ObjectUtils.toString(req.getParameter("listBlock"));
		if("".equals(listBlock)){
			vo.setListBlock(5);
		}
		ajaxResponseJson(req, res, JSONUtil.toJSON(todoImpl.getTodoList(vo)));		
	}

	/**
	 * 정보 조회
	 * @param dto
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping(value="/todo.do", params="method=getTodo")	
	public void getTodo(@ModelAttribute("dto")TodoDto dto, HttpServletRequest req, HttpServletResponse res) throws Exception {
		ajaxResponseJson(req, res, JSONUtil.toJSON(todoImpl.getTodo(dto)));		
	}
	
	/**
	 * 등록 / 수정
	 * @param dto
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping(value="/todo.do", params="method=todoProcess")	
	public void todoProcess(@ModelAttribute("dto")TodoDto dto, HttpServletRequest req, HttpServletResponse res) throws Exception {
		JSONObject node = new JSONObject();
		boolean success = todoImpl.todoProcess(dto);
		node.put("success", success);
		if(success){
			node.put("message", "save succeed");
		}else{
			node.put("message", "save failed");
		}
		ajaxResponseHtml(req, res, JSONUtil.toJSON(node));		
	}
	
	/**
	 * 삭제
	 * @param dto
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping(value="/todo.do", params="method=deleteTodo")	
	public void deleteTodo(@ModelAttribute("dto")TodoDto dto, HttpServletRequest req, HttpServletResponse res) throws Exception {
		JSONObject node = new JSONObject();
		boolean success = todoImpl.deleteTodo(dto);
		node.put("success", success);
		if(success){
			node.put("message", "delete succeed");
		}else{
			node.put("message", "delete failed");
		}
		ajaxResponseJson(req, res, JSONUtil.toJSON(node));		
	}
}
