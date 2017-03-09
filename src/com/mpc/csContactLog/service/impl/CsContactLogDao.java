package com.mpc.csContactLog.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.mpc.csContactLog.service.CsContactLogVo;

@Component
public class CsContactLogDao {

	@Resource(name = "commonSqlSession")
	private SqlSession commonSqlSession;

	public List<Object> getList(CsContactLogVo vo) throws SQLException {
		return commonSqlSession.selectList("CsContactLog.getList", vo);
	}
}
