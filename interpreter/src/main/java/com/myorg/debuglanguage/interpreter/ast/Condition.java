package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Condition implements ASTNode {
	
	private ASTNode operand1;
	private ASTNode operand2;
	private String symbol;
	
	public Condition(ASTNode operand1, ASTNode operand2, String symbol) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.symbol = symbol;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		switch (this.symbol){
		
		case "OR":
			
			if ((boolean)this.operand1.execute(symbolTable, localSymbolTable) || (boolean)this.operand2.execute(symbolTable, localSymbolTable)){
				
				return true;
			}else{
				return false;
			}
			
		case "AND":
			
			if ((boolean)this.operand1.execute(symbolTable, localSymbolTable) && (boolean)this.operand2.execute(symbolTable, localSymbolTable)){
				
				return true;
			}else{
				return false;
			}
			
		case "=":
			
			if (this.operand1.execute(symbolTable, localSymbolTable) instanceof String && this.operand2.execute(symbolTable, localSymbolTable) instanceof String) {
				
				if (this.operand1.execute(symbolTable, localSymbolTable).toString().equals(this.operand2.execute(symbolTable, localSymbolTable).toString())){
					
					return true;
				}else{
					return false;
				}
				
			}else if(this.operand1.execute(symbolTable, localSymbolTable) instanceof Integer && this.operand2.execute(symbolTable, localSymbolTable) instanceof Integer){

				if ((int)this.operand1.execute(symbolTable, localSymbolTable) == (int)this.operand2.execute(symbolTable, localSymbolTable)){
					
					return true;
				}else{
					return false;
				}
			}else{

				if ((boolean)this.operand1.execute(symbolTable, localSymbolTable) == (boolean)this.operand2.execute(symbolTable, localSymbolTable)){
					System.out.println("hereee");
					return true;
				}else{
					System.out.println("hereeemo");
					return false;
				}
			}
			
		case "<>":
			
			if (this.operand1.execute(symbolTable, localSymbolTable) instanceof String == this.operand2.execute(symbolTable, localSymbolTable) instanceof String) {
				
				if (this.operand1.execute(symbolTable, localSymbolTable).toString().equals(this.operand2.execute(symbolTable, localSymbolTable).toString())){
					
					return false;
				}else{
					return true;
				}
				
			}else{
				
				if (this.operand1.execute(symbolTable, localSymbolTable) != this.operand2.execute(symbolTable, localSymbolTable)){
					
					return false;
				}else{
					return true;
				}
			}
			
		case "<":
			
			if ((int)this.operand1.execute(symbolTable, localSymbolTable) < (int)this.operand2.execute(symbolTable, localSymbolTable)){
				
				return true;
			}else{
				return false;
			}
		
		case ">":
			
			if ((int)this.operand1.execute(symbolTable, localSymbolTable) > (int)this.operand2.execute(symbolTable, localSymbolTable)){
				
				return true;
			}else{
				return false;
			}
		
		case "<=":
			
			if ((int)this.operand1.execute(symbolTable, localSymbolTable) <= (int)this.operand2.execute(symbolTable, localSymbolTable)){
				
				return true;
			}else{
				return false;
			}
			
		case ">=":
			
			if ((int)this.operand1.execute(symbolTable, localSymbolTable) >= (int)this.operand2.execute(symbolTable, localSymbolTable)){
				
				return true;
			}else{
				return false;
			}
			
		default:
			return null;
		}
	}

}
