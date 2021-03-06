package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Repeat implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ASTNode condition;
	private List<ASTNode> body;
	
	public Repeat(ASTNode condition, List<ASTNode> body) {
		super();
		this.condition = condition;
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		do{
			for (ASTNode n : this.body) 
			{
				n.execute(symbolTable, localSymbolTable);
				
				if (!(n instanceof For || n instanceof While || n instanceof Conditional || n instanceof Repeat || n instanceof Switch)){
					
					((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(n);
				}
			}

		}while((boolean)this.condition.execute(symbolTable, localSymbolTable));
		
		return null;
	}

}
