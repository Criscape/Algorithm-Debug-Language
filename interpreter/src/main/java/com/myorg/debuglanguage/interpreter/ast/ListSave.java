package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;

public class ListSave {
	
	private List<Object> list;

	public ListSave(List<Object> list) {
		super();
		this.list = list;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
}
