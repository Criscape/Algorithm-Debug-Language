package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Assign implements ASTNode {
	
	private ASTNode id;
	private ASTNode value;
	
	public Assign(ASTNode id, ASTNode value) {
		super();
		this.id = id;
		this.value = value;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		if(localSymbolTable.containsKey(this.id.execute(symbolTable, localSymbolTable).toString())){
			
			TypeValue a = (TypeValue)localSymbolTable.get(this.id.execute(symbolTable, localSymbolTable).toString());
			a.setValue(this.value.execute(symbolTable, localSymbolTable));
			localSymbolTable.put(this.id.execute(symbolTable, localSymbolTable).toString(), a);
			
		}else if(symbolTable.containsKey(this.id.execute(symbolTable, localSymbolTable).toString())){
			
			TypeValue a = (TypeValue)symbolTable.get(this.id.execute(symbolTable, localSymbolTable).toString());
			a.setValue((Object)this.value);
			localSymbolTable.put(this.id.execute(symbolTable, localSymbolTable).toString(), a);
		}
				
		return null;
	}

	public ASTNode getId() {
		return id;
	}

	public ASTNode getValue() {
		return value;
	}
	
}
