package com.mpc.csContactLog.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mpc.csContactLog.service.CsContactLogDto;
import com.mpc.csContactLog.service.CsContactLogService;
import com.mpc.csContactLog.service.CsContactLogVo;

@Component
public class CsContactLogImpl implements CsContactLogService {
	@Resource(name = "csContactLogDao")
	private CsContactLogDao csContactLogDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<CsContactLogDto> getList(CsContactLogVo vo) throws Exception {
		ArrayList<CsContactLogDto> data = new ArrayList<CsContactLogDto>();
		data = (ArrayList)csContactLogDao.getList(vo);
		return data;
	}
}