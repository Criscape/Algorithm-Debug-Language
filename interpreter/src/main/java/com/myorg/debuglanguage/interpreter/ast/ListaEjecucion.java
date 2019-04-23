package com.myorg.debuglanguage.interpreter.ast;

import java.util.ArrayList;
import java.util.List;

public class ListaEjecucion implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ASTNode> orden;

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
}
