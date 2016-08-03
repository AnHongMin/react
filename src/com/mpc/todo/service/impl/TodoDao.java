package com.mpc.todo.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.mpc.todo.service.TodoDto;
import com.mpc.todo.service.TodoVo;

@Component
public class TodoDao {

	@Resource(name = "commonSqlSession")
	private SqlSession commonSqlSession;

	/**
	 * 총 카운트
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public int getTodoListCount(TodoVo vo) throws SQLException {
		return commonSqlSession.selectOne("Todo.getTodoListCount", vo);
	}
	
	/**
	 * 목록 Paging
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public List<Object> getTodoPagingList(TodoVo vo) throws SQLException {
		return commonSqlSession.selectList("Todo.getTodoPagingList", vo);
	}
	
	/**
	 * 정보 조회
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public TodoDto getTodo(TodoDto dto) throws SQLException {
		return commonSqlSession.selectOne("Todo.getTodo", dto);
	}
	
	/**
	 * 등록
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int insertTodo(TodoDto dto) throws SQLException {
		commonSqlSession.insert("Todo.insertTodo", dto);
		return dto.getId();
	}

	/**
	 * 수정
	 * @param dto
	 * @throws SQLException
	 */
	public void updateTodo(TodoDto dto) throws SQLException {
		commonSqlSession.update("Todo.updateTodo", dto);
	}

	/**
	 * 삭제
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int deleteTodo(TodoDto dto) throws SQLException {
		return commonSqlSession.delete("Todo.deleteTodo", dto);
	}
}
