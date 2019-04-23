package com.myorg.debuglanguage.interpreter.ast;

public abstract class Lineable {
	
	private String line;

	public Lineable(String line) {
		super();
		this.line = line;
	}
	
	public String getLine() {
		return line;
	}
}
