package com.mpc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Response 관련 유틸리티
 * 
 * @author hongmin.an
 *
 */
public class DispatchAction{

	/**
	 * Ajax Response 위한 함수.
	 * @param request
	 * @param response
	 * @param msg
	 * @param charset
	 * @param contentType
	 */
	public void ajaxResponse(HttpServletRequest request, HttpServletResponse response, Object msg, String charset, String contentType) {
		try {
			response.setContentType(contentType);
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.print(msg);
			out.flush();
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * csv response
	 * 
	 * @param request
	 * @param response
	 * @param objFile
	 */
	public void ajaxResponseMSDownload(HttpServletRequest request, HttpServletResponse response, File objFile) {
		try {
			response.setContentType("application/x-msdownload");
			String strFileName = URLEncoder.encode(new String(objFile.getName().getBytes("8859_1"), "euc-kr"), "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + strFileName + ";");		    
		    int nRead = 0;
		    byte btReadByte[] = new byte[(int)objFile.length()];
		    if(objFile.length() > 0 && objFile.isFile())
		    {
		        BufferedInputStream objBIS = new BufferedInputStream(new FileInputStream(objFile));
		        BufferedOutputStream objBOS = new BufferedOutputStream(response.getOutputStream());

		        while((nRead = objBIS.read(btReadByte)) != -1)
		            objBOS.write(btReadByte, 0, nRead);
		    
		        objBOS.flush();
		        objBOS.close();
		        objBIS.close();
		    }
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Ajax Html response.
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 */
	public void ajaxResponseHtml(HttpServletRequest request, HttpServletResponse response, Object msg) {
		ajaxResponse(request, response, msg, "utf-8", "text/html;charset=utf-8");
	}

	/**
	 * 
	 * Ajax Xml response.
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 */
	public void ajaxResponseXml(HttpServletRequest request, HttpServletResponse response, Object msg) {
		ajaxResponse(request, response, msg, "utf-8", "text/xml;charset=utf-8");
	}
	
	/**
	 * 
	 * Ajax Json response.
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 */
	public void ajaxResponseJson(HttpServletRequest request, HttpServletResponse response, Object msg) {
		ajaxResponse(request, response, msg, "utf-8", "application/x-json;charset=utf-8");
	}	
	
}
