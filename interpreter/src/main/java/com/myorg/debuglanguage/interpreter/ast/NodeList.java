package com.myorg.debuglanguage.interpreter.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NodeList implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ASTNode> components;
	private ListSave save;
	
	public NodeList(List<ASTNode> components) {
		super();
		this.components = components;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {

		List<Object> list = new ArrayList<>(); 
		
		for(ASTNode node: this.components){
			
			list.add(node.execute(symbolTable, localSymbolTable));
		}
		
		this.save = new ListSave(list);
		
		return this.save;
	}

}
