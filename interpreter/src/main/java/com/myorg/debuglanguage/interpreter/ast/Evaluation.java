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
		
		switch (this.symbol){
		
			case "+":
				return ((int)this.operand1.execute(symbolTable, localSymbolTable) + (int)this.operand2.execute(symbolTable, localSymbolTable));
			case "-":
				return ((int)this.operand1.execute(symbolTable, localSymbolTable) - (int)this.operand2.execute(symbolTable, localSymbolTable));
			case "*":
				return ((int)this.operand1.execute(symbolTable, localSymbolTable) * (int)this.operand2.execute(symbolTable, localSymbolTable));
			case "/":
				return ((int)this.operand1.execute(symbolTable, localSymbolTable) / (int)this.operand2.execute(symbolTable, localSymbolTable));
			default:
				return null;
		}
	}

}
