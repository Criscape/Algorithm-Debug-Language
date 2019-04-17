package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Retorno implements ASTNode {
	
	private ASTNode node;
	
	public Retorno(ASTNode node) {
		super();
		this.node = node;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		return node.execute(symbolTable, localSymbolTable);
	}

}
