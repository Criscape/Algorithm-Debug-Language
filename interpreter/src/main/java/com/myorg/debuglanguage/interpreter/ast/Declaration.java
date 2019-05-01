package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class Declaration implements ASTNode,java.io.Serializable,Lineable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dataType;
	private List<ASTNode> idList;
	private String ioType;
	private String line;
	
	public Declaration(String dataType, List<ASTNode> idList,String line) {
		super();
		this.dataType = dataType;
		this.idList = idList;
		this.ioType = "";
		this.line = line;
	}

	@Override
	public String getLine() {
		// TODO Auto-generated method stub
		return this.line;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}



	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}



	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		for(ASTNode id: this.idList){
			
			String key = id.execute(symbolTable, localSymbolTable).toString(); 
			
			if (key.contains("[")){
				
				int index = key.indexOf('[')+1;
				int finalIndex = key.indexOf(']');
				int size = Integer.parseInt(key.substring(index, finalIndex));
				localSymbolTable.put(key.substring(0, index-1), null);
				for (int i = 0; i< size; i++){
					
					TypeValue x = new TypeValue(this.dataType,"in",null);
					x.setVectorSize(size);
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
