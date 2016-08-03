package com.mpc.todo.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mpc.todo.service.TodoDto;
import com.mpc.todo.service.TodoService;
import com.mpc.todo.service.TodoVo;
import com.mpc.util.json.JSONObject;
import com.mpc.util.json.JSONUtil;

@Component
public class TodoImpl implements TodoService {
	@Resource(name = "todoDao")
	private TodoDao todoDao;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject getTodoList(TodoVo vo) throws Exception {
		JSONObject node = new JSONObject();
		ArrayList<TodoDto> data = new ArrayList<TodoDto>(); 
		int totalCount = todoDao.getTodoListCount(vo);
		if(totalCount == 0){
		}else{
			vo.setTotalCount(totalCount);
			vo.checkPage();
			data = (ArrayList)todoDao.getTodoPagingList(vo);			
		}
		node.put("total", totalCount);
		node.put("data", JSONUtil.toJSON(data));
		node.put("vo", JSONUtil.toJSON(vo));
		return node;
	}
	
	public JSONObject getTodo(TodoDto dto) throws Exception {
		JSONObject node = new JSONObject();
		if(dto.getId()==0){
			node.put("success", false);
		}else{
			
			//정보 조회
			TodoDto data = todoDao.getTodo(dto);
			node.put("success", true);
			node.put("data", JSONUtil.toJSON(data));
		}
		return node;
	}
	
	public boolean todoProcess(TodoDto dto) throws Exception {
		int id = dto.getId();
		if(id==0){
			id = todoDao.insertTodo(dto);
		}else{
			todoDao.updateTodo(dto);
		}
		if(id > 0){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteTodo(TodoDto dto) throws Exception {
		int result = todoDao.deleteTodo(dto);
		return (result > 0)?true:false;
	}
}