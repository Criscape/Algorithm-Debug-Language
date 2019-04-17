package com.myorg.debuglanguage.interpreter.ast;

import java.util.List;

public class Case {
	
	private ASTNode evaluator;
	private List<ASTNode> body;
	
	public Case(ASTNode evaluator, List<ASTNode> body) {
		super();
		this.evaluator = evaluator;
		this.body = body;
	}

	public ASTNode getEvaluator() {
		return evaluator;
	}

	public List<ASTNode> getBody() {
		return body;
	}
	
}
