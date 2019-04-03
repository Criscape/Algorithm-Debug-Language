package com.myorg.debuglanguage.interpreter.ast;

public class Constant implements ASTNode {
	
	private Object value;
	
	
	
	public Constant(Object value) {
		super();
		this.value = value;
	}



	@Override
	public Object execute() {

		return this.value;
	}

}
