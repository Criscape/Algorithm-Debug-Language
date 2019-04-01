package com.myorg.debuglanguage.interpreter.ast;

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
	public Object execute() {
		
		switch (this.symbol){
		
			case "+":
				return ((int)this.operand1.execute() + (int)this.operand2.execute());
			case "-":
				return ((int)this.operand1.execute() - (int)this.operand2.execute());
			case "*":
				return ((int)this.operand1.execute() * (int)this.operand2.execute());
			case "/":
				return ((int)this.operand1.execute() / (int)this.operand2.execute());
			default:
				return null;
		}
	}

}
