package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Switch implements ASTNode {

	private ASTNode id;
	private List<Case> cases;
	private List<ASTNode> defaultCase;
	
	public Switch(ASTNode id, List<Case> cases, List<ASTNode> defaultCase) {
		super();
		this.id = id;
		this.cases = cases;
		this.defaultCase = defaultCase;
	}

	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		Object a = id.execute(symbolTable, localSymbolTable);
		boolean executed = false;
		
		if (a instanceof String){
			
			for(Case x: cases){
				
				if( ((String)a).equals((String)x.getEvaluator().execute(symbolTable, localSymbolTable)) ){
					
					for(ASTNode y: x.getBody()){
						
						y.execute(symbolTable, localSymbolTable);
					}
					
					executed = true;
				}
			}
			
			if (!executed){
				
				for(ASTNode z: defaultCase){
					
					z.execute(symbolTable, localSymbolTable);
				}
			}
			
		}else if(a instanceof Integer){
			
				for(Case x: cases){
				
				if( (Integer)a == (Integer)x.getEvaluator().execute(symbolTable, localSymbolTable) ){
					
					for(ASTNode y: x.getBody()){
						
						y.execute(symbolTable, localSymbolTable);
					}
					
					executed = true;
				}
			}
			
			if(!executed){
				
				for(ASTNode z: defaultCase){
					
					z.execute(symbolTable, localSymbolTable);
				}
			}
			
		}
		
		return null;
	}

}
