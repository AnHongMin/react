package com.mpc.csContactLog.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mpc.csContactLog.service.CsContactLogDto;
import com.mpc.csContactLog.service.CsContactLogService;
import com.mpc.csContactLog.service.CsContactLogVo;
import com.mpc.util.DateUtil;
import com.mpc.util.DispatchAction;


@Controller
public class CsContactLogController extends DispatchAction {
	@Resource(name="csContactLogImpl")
	CsContactLogService csContactLogImpl;
	
	@RequestMapping(value="/csContactLog.do", params="method=getList")	
	public ModelAndView getList(@ModelAttribute("vo")CsContactLogVo vo, HttpServletRequest req, HttpServletResponse res) throws Exception {		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csContactLog/getList");
		
		if(vo.getDate()!=null){
			vo.setStart_dt(DateUtil.strGetStartMonthDate(vo.getDate()));
			vo.setEnd_dt(DateUtil.strGetEndMonthDate(vo.getDate()));
			
			ArrayList<CsContactLogDto> list = csContactLogImpl.getList(vo);
			mav.addObject("list",list);
		}
		
		res.setContentType("application/vnd.ms-excel; charset=UTF-8");
		res.setHeader("Content-Disposition", "attachment; filename=csContactLog.xls");
		return mav;
	}
}
