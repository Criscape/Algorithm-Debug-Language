package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class ForExec implements ASTNode, java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private int action;
	private ASTNode assignation;
	private ASTNode limit;
	private ASTNode increment;
	private ASTNode ascDecType;
	private List<ASTNode> body;
	private String idIN;
	private int incIN;
	
	public ForExec(int action, ASTNode assignation, ASTNode limit, ASTNode increment, ASTNode ascDecType,
			List<ASTNode> body,String idIN, int incIN) {
		super();
		this.action = action;
		this.assignation = assignation;
		this.limit = limit;
		this.increment = increment;
		this.ascDecType = ascDecType;
		this.body = body;
		this.idIN = idIN;
		this.incIN = incIN;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		// TODO Auto-generated method stub
		
		if(this.action == 1){
			
			Assign a = (Assign)this.assignation;
			String id = a.getId().execute(symbolTable, localSymbolTable).toString();
			TypeValue value = new TypeValue("int","in",a.getValue().execute(symbolTable, localSymbolTable));
			localSymbolTable.put(id, value);
			int x_limit = (int)this.limit.execute(symbolTable, localSymbolTable);
			int x_increment = (int)this.increment.execute(symbolTable, localSymbolTable);
			String x_type = this.ascDecType.execute(symbolTable, localSymbolTable).toString();
		}
		
		if(this.action == 2){
			
			int new_value = ((Integer)((TypeValue)localSymbolTable.get(this.idIN)).getValue()) + this.incIN; 
			TypeValue v_x = new TypeValue("int","in", new_value);
			localSymbolTable.put(this.idIN, v_x);
		}
		
		if(this.action == 3){
			
			int new_value = ((Integer)((TypeValue)localSymbolTable.get(this.idIN)).getValue()) - this.incIN; 
			TypeValue v_x = new TypeValue("int","in", new_value);
			localSymbolTable.put(this.idIN, v_x);
		}
		
		if(this.action == 4){
			
			localSymbolTable.remove(this.idIN);
		}
		
		return null;
	}

}
