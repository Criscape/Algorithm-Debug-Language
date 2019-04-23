package com.myorg.debuglanguage.interpreter;
import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

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

		debugGrammarLexer lexer2 = new debugGrammarLexer(new ANTLRFileStream(program));
		
		/*String aux = "";
		int auxint = 1;
		for(Token node : lexer2.getAllTokens()){
			if(node.getLine() == auxint){
				aux += node.getText();
			}
			else{
				aux += "\n";
				aux += node.getText();
				auxint++;
			}
		}*/
		
		//System.out.println(aux);
	}

}
