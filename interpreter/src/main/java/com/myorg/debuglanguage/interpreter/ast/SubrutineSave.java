package com.myorg.debuglanguage.interpreter.ast;

import java.util.ArrayList;
import java.util.List;

public class SubrutineSave {
	
	//private String dataType;
	private String funcOrProc;
	private String name;
	private List<ASTNode> parameters;
	private List<ASTNode> declarations;
	private List<ASTNode> body;
	private NaryTreeNode last;
	private List<NaryTreeNode> arboles;
	
	public SubrutineSave(String funcOrProc,String name, List<ASTNode> parameters, List<ASTNode> declarations, List<ASTNode> body) {
		super();
		this.funcOrProc = funcOrProc;
		this.name = name;
		this.parameters = parameters;
		this.declarations = declarations;
		this.body = body;
		this.arboles = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<ASTNode> getParameters() {
		return parameters;
	}

	public List<ASTNode> getDeclarations() {
		return declarations;
	}

	public List<ASTNode> getBody() {
		return body;
	}
	
	public String getFuncOrProc(){
		return funcOrProc;
	}
	
	public List<NaryTreeNode> getArboles() {
		return arboles;
	}

	public void setArboles(List<NaryTreeNode> arboles) {
		this.arboles = arboles;
	}

	public NaryTreeNode getLast(){
		return this.last;
	}
	
	public void setLast(NaryTreeNode last){
		this.last = last;
	}
}
