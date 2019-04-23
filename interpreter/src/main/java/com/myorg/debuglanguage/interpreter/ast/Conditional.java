package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Conditional extends Lineable implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;
	
	public Conditional(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody, String line) {
		super(line);
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
			}
		}
		else
		{		
			for (ASTNode n : this.elseBody)
			{
				n.execute(symbolTable, localSymbolTable);
			}
		}
		return null;
	}

}
