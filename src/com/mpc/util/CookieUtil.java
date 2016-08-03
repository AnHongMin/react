package com.mpc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 쿠키 관련 유틸리티
 * 
 * @author hongmin.an
 * 
 */
public class CookieUtil {
	
	public static final String COOKIE_USERID = "userid";
	public static final String COOKIE_BRANCHID = "branchid";
	public static final String COOKIE_BRANCHNAME = "branchname";

	public static final int COOKIE_TIME = 30*60; // 분 x 초
	
	/**
	 * 새로운 쿠키를 생성해서 HttpServletResponse에 추가한다.
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param cookieName
	 *            String
	 * @param cookieValue
	 *            String
	 * @param domain
	 *            String
	 * @param path
	 *            String
	 */
	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain, String path) {
		Cookie cookie;
		cookie = new Cookie(cookieName, cookieValue);
		if (domain != null)
			cookie.setDomain(domain);
		if (path != null)
			cookie.setPath(path);
		cookie.setMaxAge(CookieUtil.COOKIE_TIME);
		response.addCookie(cookie);
	}

	/**
	 * 새로운 쿠키를 생성해서 HttpServletResponse에 추가한다.
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param cookieName
	 *            String
	 * @param cookieValue
	 *            String
	 * @param domain
	 *            String
	 * @param path
	 *            String
	 * @param maxAge
	 *            쿠키의 생존시간
	 */
	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain, String path, int maxAge) {
		Cookie cookie;
		cookie = new Cookie(cookieName, cookieValue);
		if (domain != null)
			cookie.setDomain(domain);
		if (path != null)
			cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 지정한 이름의 쿠키를 리턴한다. 존재하지 않을 경우 null을 리턴한다.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param cookieName
	 *            String
	 * @return the cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0)
			return null;

		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().compareTo(cookieName) == 0) {
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 지정한 이름의 쿠키값을 리턴한다.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param cookieName
	 *            String
	 * @return the cookie value
	 */
	public static String getCookieValue(HttpServletRequest request, HttpServletResponse res, String cookieName) {
		Cookie[] cookies = request.getCookies();
		String returnVal = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					returnVal = cookies[i].getValue();
					CookieUtil.addCookie(res, cookieName, returnVal, null, "/");
				}
			}
		}
		return returnVal;
	}
}