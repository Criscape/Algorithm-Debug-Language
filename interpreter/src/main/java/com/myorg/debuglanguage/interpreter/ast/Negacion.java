package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Negacion implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ASTNode nodo;
	
	public Negacion(ASTNode nodo) {
		super();
		this.nodo = nodo;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		return !((Boolean)nodo.execute(symbolTable, localSymbolTable));
	}

}
