package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Constant implements ASTNode {
	
	private Object value;
	private String symbol;
	
	public Constant(Object value,String symbol) {
		super();
		this.value = value;
		this.symbol = symbol;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		if (this.symbol.equals("-"))
		{
			return (int)this.value*-1;
		}else if(this.symbol.equals("str")){
			
			String aux = ((String)this.value).substring(1, ((String)this.value).length() - 1);
			
			return aux;
		}
		
		return this.value;
	}

}
