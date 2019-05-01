package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Conditional implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;
	
	public Conditional(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		if ((boolean)this.condition.execute(symbolTable, localSymbolTable))
		{	
			for (ASTNode n : this.body)
			{
				n.execute(symbolTable, localSymbolTable);
				if (!(n instanceof For || n instanceof While || n instanceof Conditional)){
					
					((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(n);
				}
			}
		}
		else
		{		
			for (ASTNode n : this.elseBody)
			{
				n.execute(symbolTable, localSymbolTable);
				if (!(n instanceof For || n instanceof While || n instanceof Conditional)){
					
					((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(n);
				}
			}
		}
		return null;
	}

}
