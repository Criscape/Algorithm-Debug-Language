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
			//n.execute(symbolTable, localSymbolTable);
			
			if(n instanceof SubRutExec){
				
				n.execute(symbolTable, localSymbolTable);
				symbolTable.put("ejecuto", true);
				n.execute(symbolTable, localSymbolTable);
				symbolTable.put("ejecuto", false);

			}else{

				((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(n);
			}
			
		}
				
		symbolTable.put("guardo", true);//?
		
		return null;
	}

}
