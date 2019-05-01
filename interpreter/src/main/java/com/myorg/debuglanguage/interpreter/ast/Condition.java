package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Condition implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		
		Object obj1 = this.operand1.execute(symbolTable, localSymbolTable);
		Object obj2 = this.operand2.execute(symbolTable, localSymbolTable);
		
		switch (this.symbol){
		
		case "OR":
			
			if ((boolean)obj1 || (boolean)obj2){
				
				return true;
			}else{
				return false;
			}
			
		case "AND":
			
			if ((boolean)obj1 && (boolean)obj2){
				
				return true;
			}else{
				return false;
			}
			
		case "=":
			
			if (obj1 instanceof String && obj2 instanceof String) {
				
				if (obj1.toString().equals(obj2.toString())){
					
					return true;
				}else{

					return false;
				}
				
			}else if(obj1 instanceof Integer && obj2 instanceof Integer){

				if ((int)obj1 == (int)obj2){
					
					return true;
				}else{
					return false;
				}
			}else{

				if ((boolean)obj1 == (boolean)obj2){
					
					return true;
				}else{
					
					return false;
				}
			}
			
		case "<>":
			
			if (obj1 instanceof String == obj2 instanceof String) {
				
				if (obj1.toString().equals(obj2.toString())){
					
					return false;
				}else{
					return true;
				}
				
			}else{
				
				if (obj1 != obj2){
					
					return false;
				}else{
					return true;
				}
			}
			
		case "<":

			if ((int)obj1 < (int)obj2){
				
				return true;
			}else{
				return false;
			}
		
		case ">":
			
			if ((int)obj1 > (int)obj2){
				
				return true;
			}else{
				return false;
			}
		
		case "<=":
			
			if ((int)obj1 <= (int)obj2){
				
				return true;
			}else{
				return false;
			}
			
		case ">=":
			
			if ((int)obj1 >= (int)obj2){
				
				return true;
			}else{
				return false;
			}
			
		default:
			return null;
		}
	}

}
