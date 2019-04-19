package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Evaluation implements ASTNode {
	
	private ASTNode operand1;
	private ASTNode operand2;
	private String symbol;
	
	public Evaluation(ASTNode operand1, ASTNode operand2, String symbol) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.symbol = symbol;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		Object obj1 = this.operand1.execute(symbolTable, localSymbolTable);
		Object obj2 = this.operand2.execute(symbolTable, localSymbolTable);
		
		switch (this.symbol){	

			case "+":
				if (obj1 instanceof String && obj2 instanceof String){
					
					return ((String)obj1 + (String)obj2);
					
				}else if(obj1 instanceof String){
					
					return ((String)obj1 + (int)obj2);
					
				}else if(obj2 instanceof String){
					
					return ((int)obj1 + (String)obj2);
				}
				
				return ((int)obj1 + (int)obj2);
			case "-":
				return ((int)obj1 - (int)obj2);
			case "*":
				return ((int)obj1 * (int)obj2);
			case "/":
				return ((int)obj1 / (int)obj2);
			default:
				return null;
		}
	}

}
