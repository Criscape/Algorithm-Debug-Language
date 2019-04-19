package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Parameter implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ioType;
	private String dataType;
	private String id;
	
	public Parameter(String ioType, String dataType, String id) {
		super();
		this.ioType = ioType;
		this.dataType = dataType;
		this.id = id;
	}
	
	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		return null;
	}

	public String getIoType() {
		return ioType;
	}

	public String getDataType() {
		return dataType;
	}

	public String getId() {
		return id;
	}
	
}
