package com.myorg.debuglanguage.interpreter.ast;

import java.util.ArrayList;
import java.util.List;

public class ListaEjecucion implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ASTNode> orden;
	private List<Boolean> executed;

	public List<ASTNode> getOrden() {
		return orden;
	}

	public void setOrden(List<ASTNode> orden) {
		this.orden = orden;
	}

	public ListaEjecucion(List<ASTNode> orden) {
		super();
		this.orden = orden;
	}
	
	public List<Boolean> getExecuted() {
		return executed;
	}
	
	public void setExecuted(List<Boolean> executed) {
		this.executed = executed;
	}
	
	public void initExecuted(){
		
		this.executed = new ArrayList<>();
		
		for(int i=0; i<this.orden.size(); i++){
			
			this.executed.add(false);
		}
	}
}
