package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public interface ASTNode {
	
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable);

}
