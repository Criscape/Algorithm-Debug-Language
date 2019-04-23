package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Retorno extends Lineable implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ASTNode node;
	
	public Retorno(ASTNode node, String line) {
		super(line);
		this.node = node;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		return node.execute(symbolTable, localSymbolTable);
	}

}
