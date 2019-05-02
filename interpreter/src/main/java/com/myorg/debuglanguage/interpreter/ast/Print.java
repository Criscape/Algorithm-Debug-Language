package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Print implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ASTNode arg;
	
	public Print(ASTNode arg) {
		super();
		this.arg = arg;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		Object x = this.arg.execute(symbolTable, localSymbolTable);
		
		if(x instanceof Integer){
			
			System.out.println(x.toString());

		}else if(x instanceof String || x instanceof Boolean){
			
			System.out.println(x.toString());
		}else{
			
			System.out.println(x);
		}
		
		return null;
	}

}
