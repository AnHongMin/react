package com.mpc.todo.service;

import com.mpc.util.Paging;

public class TodoVo extends Paging{
	
	private String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
