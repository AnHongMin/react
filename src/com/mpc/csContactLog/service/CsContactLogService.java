package com.mpc.csContactLog.service;

import java.util.ArrayList;

public interface CsContactLogService {
	public ArrayList<CsContactLogDto> getList(CsContactLogVo vo) throws Exception;
}
