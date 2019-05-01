package com.myorg.debuglanguage.interpreter.ast;

import java.util.Map;

public class Assign implements ASTNode,java.io.Serializable,Lineable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ASTNode id;
	private ASTNode value;
	private String line;
	
	public Assign(ASTNode id, ASTNode value, String line) {
		super();
		this.id = id;
		this.value = value;
		this.line = line;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		String key = this.id.execute(symbolTable, localSymbolTable).toString();
		Object val = this.value.execute(symbolTable, localSymbolTable);
		
		if(localSymbolTable.containsKey(key)){

			if(val instanceof ListSave){
				
				if(localSymbolTable.containsKey(key+"[0]")){
					
					ListSave listsave = (ListSave)val;
					
					TypeValue reference = (TypeValue)localSymbolTable.get(key+"[0]");
					
					for(int i = 0; i < reference.getVectorSize(); i++){
						
						TypeValue value_put = (TypeValue)localSymbolTable.get(key+"["+i+"]");
						value_put.setValue(listsave.getList().get(i));
						localSymbolTable.put(key+"["+i+"]", value_put);
					}
					
					return null;
				}
			}
			
			TypeValue a = (TypeValue)localSymbolTable.get(key);
			a.setValue(val);
			localSymbolTable.put(key, a);
			
		}else if(symbolTable.containsKey(key)){
			
			TypeValue a = (TypeValue)symbolTable.get(key);
			a.setValue((Object)this.value);
			localSymbolTable.put(key, a);
		}
				
		return null;
	}
	
	@Override
	public String getLine() {		
		return this.line;
	}

	public ASTNode getId() {
		return id;
	}

	public ASTNode getValue() {
		return value;
	}
	
}
