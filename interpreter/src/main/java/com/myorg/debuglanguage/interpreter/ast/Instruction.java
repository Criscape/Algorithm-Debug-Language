package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Instruction implements ASTNode {
	
	private List<ASTNode> body;
	
	public Instruction(List<ASTNode> body) {
		super();
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		return this.body;
	}

}
