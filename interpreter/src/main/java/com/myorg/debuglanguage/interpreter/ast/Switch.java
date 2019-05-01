package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Switch implements ASTNode,java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
				
				Object obj1 = x.getEvaluator().execute(symbolTable, localSymbolTable);
				
				if(obj1 instanceof String){
					
					if( ((String)a).equals((String)obj1) ){
						
						for(ASTNode y: x.getBody()){
							
							y.execute(symbolTable, localSymbolTable);
							if (!(y instanceof For || y instanceof While || y instanceof Conditional || y instanceof Repeat || y instanceof Switch)){
								
								((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(y);
							}
						}
						
						executed = true;
					}
				}
				
			}
			
			if (!executed){
				
				for(ASTNode z: defaultCase){
					
					z.execute(symbolTable, localSymbolTable);
					if (!(z instanceof For || z instanceof While || z instanceof Conditional || z instanceof Repeat || z instanceof Switch)){
						
						((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(z);
					}
				}
			}
			
		}else if(a instanceof Integer){
			
			for(Case x: cases){
				
				Object obj1 = x.getEvaluator().execute(symbolTable, localSymbolTable);
				
				if (obj1 instanceof Integer){
					
					if( (Integer)a == (Integer)obj1 ){
						
						for(ASTNode y: x.getBody()){
							
							y.execute(symbolTable, localSymbolTable);
							if (!(y instanceof For || y instanceof While || y instanceof Conditional || y instanceof Repeat || y instanceof Switch)){
								
								((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(y);
							}
						}
						
						executed = true;
					}
				}
				
			}
			
			if(!executed){
				
				for(ASTNode z: defaultCase){
					
					z.execute(symbolTable, localSymbolTable);

					if (!(z instanceof For || z instanceof While || z instanceof Conditional || z instanceof Repeat || z instanceof Switch)){
						
						((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(z);
					}
				}
			}
			
		}
		
		return null;
	}

}
