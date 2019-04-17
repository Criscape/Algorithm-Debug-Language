package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class NodeList implements ASTNode {
	
	private List<ASTNode> components;
	
	public NodeList(List<ASTNode> components) {
		super();
		this.components = components;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		return this.components;
	}

}
