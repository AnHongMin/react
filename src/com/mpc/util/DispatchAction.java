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
			
			// POST, GET, OPTIONS, DELETE 요청에 대해 허용하겠다는 의미입니다.
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			/*
			 * HTTP Request 요청에 앞서 Preflight Request 라는 요청이 발생되는데, 이는 해당 서버에 요청하는 메서드가 실행 가능한지(권한이 있는지) 확인을 위한 요청입니다. 
			 * Preflight Request는 OPTIONS 메서드를 통해 서버에 전달됩니다. (위의 Methods 설정에서 OPTIONS 를 허용해 주었습니다.)
			 * 여기서 Access-Control-Max-Age 는 Preflight request를 캐시할 시간입니다. 단위는 초단위이며, 3,600초는 1시간입니다. 
			 * Preflight Request를 웹브라우저에 캐시한다면 최소 1시간동안에는 서버에 재 요청하지 않을 것입니다.
			 */
			response.setHeader("Access-Control-Max-Age", "3600");
			
			/*
			 * 이는 표준화된 규약은 아니지만, 보통 AJAX 호출이라는 것을 의미하기 위해 비공식적으로 사용되는 절차입니다.
			 * JQuery 또한 AJAX 요청 시, 이 헤더(x-requested-with)를 포함하는 것을 확인하실 수 있습니다.
			 * 여기서는 이 요청이 Ajax 요청임을 알려주기 위해 Header 에 x-request-width를 설정합니다.
			 * Form을 통한 요청과 Ajax 요청을 구분하기 위해 사용된 비표준 규약지만, 많은 라이브러리에서 이를 채택하여 사용하고 있습니다. 
			 * (참고로 HTML5 부터는 Form 과 Ajax 요청을 구분할 수 있는 Header가 추가되었습니다.)
			 */
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			
			/*
			 * 이 부분이 가장 중요한 부분입니다. * 는 모든 도메인에 대해 허용하겠다는 의미입니다. 
			 * 즉 어떤 웹사이트라도 이 서버에 접근하여 AJAX 요청하여 결과를 가져갈 수 있도록 허용하겠다는 의미입니다.
			 * 만약 보안 이슈가 있어서 특정 도메인만 허용해야 한다면 * 대신 특정 도메인만을 지정할 수 있습니다
			 */
		    response.setHeader("Access-Control-Allow-Origin", "*");
		    
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
