package com.mpc.test;

import com.mpc.util.Paging;

public class PageTest {

	public static void main(String[] args) {
	
		try {
			Paging page = new Paging();
			page.setPage(381);
			page.setTotalCount(1901);
			page.checkPage();
			System.out.println(page.getStartNum() + " : "+ page.getEndNum());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

