package com.mpc.todo.service;

import com.mpc.util.json.JSONObject;

public interface TodoService {
	/**
	 * 목록 Paging
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public JSONObject getTodoList(TodoVo vo) throws Exception;

	/**
	 * 정보 조회
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public JSONObject getTodo(TodoDto dto) throws Exception;

	/**
	 * 등록 / 수정
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean todoProcess(TodoDto dto) throws Exception;

	/**
	 * 삭제
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean deleteTodo(TodoDto dto) throws Exception;
}
