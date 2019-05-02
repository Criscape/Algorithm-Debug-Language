package com.myorg.debuglanguage.interpreter.ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainExec implements ASTNode,java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ASTNode> body;
	
	public MainExec(List<ASTNode> body) {
		super();
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		localSymbolTable = new HashMap<>();
		
		for (ASTNode n : this.body) {
			
			if (n instanceof For || n instanceof While || n instanceof Conditional || n instanceof Repeat || n instanceof Switch){

				n.execute(symbolTable, localSymbolTable);
			}else{

				n.execute(symbolTable, localSymbolTable);
				((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(n);	
			}


			
		}
						
		return null;
	}
	
}
