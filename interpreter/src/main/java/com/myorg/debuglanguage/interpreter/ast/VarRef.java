package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class VarRef implements ASTNode {

	private ASTNode name;
	
	public VarRef(ASTNode name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		String key = this.name.execute(symbolTable, localSymbolTable).toString();
		
		if(localSymbolTable.containsKey(key)){
			
			return ((TypeValue)localSymbolTable.get(key)).getValue();
		}
		
		return ((TypeValue)symbolTable.get(key)).getValue();
	}

}
