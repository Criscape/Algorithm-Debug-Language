package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Declaration implements ASTNode {
	
	private String dataType;
	private List<ASTNode> idList;
	private String ioType;
	
	public Declaration(String dataType, List<ASTNode> idList) {
		super();
		this.dataType = dataType;
		this.idList = idList;
		this.ioType = "";
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		for(ASTNode id: this.idList){
			
			String key = id.execute(symbolTable, localSymbolTable).toString(); 
			
			if (key.contains("[")){
				
				int index = key.indexOf('[')+1;
				int size = Integer.parseInt(key.substring(index, index+1));
				for (int i = 0; i< size; i++){
					
					TypeValue x = new TypeValue(this.dataType,"in",null);
					localSymbolTable.put(key.substring(0, index-1)+"["+i+"]", x);
				}
			}else{
				
				TypeValue x = new TypeValue(this.dataType,"in",null);
				localSymbolTable.put(key, x);
			}
		}
		return null;
	}
	
	public List<ASTNode> getList(){
		return idList;
	}
}
