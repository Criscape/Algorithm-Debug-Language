package com.myorg.debuglanguage.interpreter;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

	private static final String EXTENSION = "adl";

	public static void main(String[] args) throws IOException {
		String program = args.length > 1 ? args[1] : "test/test." + EXTENSION;

		System.out.println("Interpreting file " + program);
		
		debugGrammarLexer lexer = new debugGrammarLexer(new ANTLRFileStream(program));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		debugGrammarParser parser = new debugGrammarParser(tokens);

		debugGrammarParser.ProgramContext tree = parser.program();
		
		debugGrammarCustomVisitor visitor = new debugGrammarCustomVisitor();
		visitor.visit(tree);

		System.out.println("Interpretation finished");

	}

}
