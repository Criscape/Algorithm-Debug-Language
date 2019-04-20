package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;
import java.util.Map;

public class ListaDebug implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Map<String,Object>> entorno;
	
	public ListaDebug(List<Map<String, Object>> entorno) {
		super();
		this.entorno = entorno;
	}

	public List<Map<String, Object>> getEntorno() {
		return entorno;
	}

	public void setEntorno(List<Map<String, Object>> entorno) {
		this.entorno = entorno;
	}
	
}
