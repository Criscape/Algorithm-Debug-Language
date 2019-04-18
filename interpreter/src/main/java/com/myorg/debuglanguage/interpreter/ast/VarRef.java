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
		
		if (key.contains("[")){
			
			int index = key.indexOf('[')+1;
			int finalIndex = key.indexOf(']');
			String content = key.substring(index, finalIndex);
			
			if(!(content.matches("-?\\d+(\\.\\d+)?"))){
				
				if(localSymbolTable.containsKey(content)){
					
					VarRef c = new VarRef(new Constant(content,""));
					return ((TypeValue)localSymbolTable.get(key.substring(0, index-1)+"["+c.execute(symbolTable, localSymbolTable)+"]")).getValue();
				}
				
				return null;
			}
			
		}
		
		if(localSymbolTable.containsKey(key)){
			
			return ((TypeValue)localSymbolTable.get(key)).getValue();
		}
		
		return ((TypeValue)symbolTable.get(key)).getValue();
	}

}
