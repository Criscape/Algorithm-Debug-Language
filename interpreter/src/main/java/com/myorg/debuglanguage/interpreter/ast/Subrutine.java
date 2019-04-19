package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Subrutine implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dataType;
	private String funcOrProc;
	private String name;
	private List<ASTNode> parameters;
	private List<ASTNode> declarations;
	private List<ASTNode> body;
	
	public Subrutine(String dataType, String funcOrProc, String name, List<ASTNode> parameters,
			List<ASTNode> declarations, List<ASTNode> body) {
		super();
		this.dataType = dataType;
		this.funcOrProc = funcOrProc;
		this.name = name;
		this.parameters = parameters;
		this.declarations = declarations;
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
				
		for (ASTNode n: this.declarations){
			
			if (!(n instanceof Declaration)){
				//error
			}
		}
		
		//Busqueda del return y garantizar que devuelva el tipo que es
		if (this.funcOrProc.equals("function")){
			
			
		}
		
		if (symbolTable.containsKey(this.name)){	
			//error
		}else{
			SubrutineSave x = new SubrutineSave(this.funcOrProc,this.name,
					this.parameters, this.declarations, this.body);
			symbolTable.put(this.name,x);
		}
				
		return null;
	}

}
