package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class For implements ASTNode,java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ASTNode assignation;
	private ASTNode limit;
	private ASTNode increment;
	private ASTNode ascDecType;
	private List<ASTNode> body;
	
	public For(ASTNode assignation, ASTNode limit, ASTNode increment, ASTNode ascDecType, List<ASTNode> body) {
		super();
		this.assignation = assignation;
		this.limit = limit;
		this.increment = increment;
		this.ascDecType = ascDecType;
		this.body = body;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		Assign a = (Assign)this.assignation;
		String id = a.getId().execute(symbolTable, localSymbolTable).toString();
		TypeValue value = new TypeValue("int","in",a.getValue().execute(symbolTable, localSymbolTable));
		localSymbolTable.put(id, value);
		int x_limit = (int)this.limit.execute(symbolTable, localSymbolTable);
		int x_increment = (int)this.increment.execute(symbolTable, localSymbolTable);
		String x_type = this.ascDecType.execute(symbolTable, localSymbolTable).toString();

		ForExec new_for = new ForExec(1,this.assignation,this.limit,this.increment,this.ascDecType,this.body,id,x_increment);
		((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(new_for);	
		
		if (x_type.equals("asc")){
			
			for (int i = Integer.parseInt(a.getValue().execute(symbolTable, localSymbolTable).toString()); i <= x_limit; i = i + x_increment){
				
				for (ASTNode x: this.body){
					
					x.execute(symbolTable, localSymbolTable);
					if (!(x instanceof For || x instanceof While || x instanceof Conditional)){
						
						((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(x);
					}	
				}
				
				int new_value = ((Integer)((TypeValue)localSymbolTable.get(id)).getValue()) + x_increment; 
				TypeValue v_x = new TypeValue("int","in", new_value);
				localSymbolTable.put(id, v_x);
				
				ForExec new_for2 = new ForExec(2,this.assignation,this.limit,this.increment,this.ascDecType,this.body,id,x_increment);
				((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(new_for2);
			}
		}else{
			
			for (int i = Integer.parseInt(a.getValue().execute(symbolTable, localSymbolTable).toString()); i >= x_limit; i = i - x_increment){
				
				for (ASTNode x: this.body){
					
					x.execute(symbolTable, localSymbolTable);
					if (!(x instanceof For || x instanceof While || x instanceof Conditional)){
						
						((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(x);
					}
				}
				
				int new_value = ((Integer)((TypeValue)localSymbolTable.get(id)).getValue()) - x_increment; 
				TypeValue v_x = new TypeValue("int","in", new_value);
				localSymbolTable.put(id, v_x);
				
				ForExec new_for2 = new ForExec(3,this.assignation,this.limit,this.increment,this.ascDecType,this.body,id,x_increment);
				((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(new_for2);
			}
		}
		
		localSymbolTable.remove(a.getId().toString());
		
		ForExec new_for3 = new ForExec(4,this.assignation,this.limit,this.increment,this.ascDecType,this.body,id,x_increment);
		((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(new_for3);
		
		return null;
	}
	
}
