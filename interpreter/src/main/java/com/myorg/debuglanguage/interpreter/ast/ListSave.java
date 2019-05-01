package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;

public class ListSave implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
