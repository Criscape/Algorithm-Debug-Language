grammar debugGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
	import java.util.List;
	import java.util.ArrayList;
	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectOutputStream;
	import com.myorg.debuglanguage.interpreter.ast.*;
}

@parser::members {
	/*Map<String, Object> symbolTable = new HashMap<>();*/
}

program: {List<ASTNode> subrt = new ArrayList<>();}
 ( subrutine {subrt.add($subrutine.node);} )* main
{
	Map<String,Object> symbolTable = new HashMap();
	Map<String,Object> localSymbolTable = new HashMap();
	
	List<ASTNode> debuggerList = new ArrayList<>();
	ListaEjecucion lista_exec = new ListaEjecucion(debuggerList);
	symbolTable.put("lista_exec",lista_exec);
	
	for (ASTNode n : subrt){
		
		n.execute(symbolTable,localSymbolTable);
		((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add(n);
	}
	
	$main.node.execute(symbolTable,localSymbolTable);
	((ListaEjecucion)symbolTable.get("lista_exec")).getOrden().add($main.node);
	
	
	String filename = "test/lista_ejecucion.ntn";
	
	File f = new File(filename);
	
	if(f.exists()){
		f.delete();
	}
	
	try{
		
		FileOutputStream file = new FileOutputStream(filename);
							
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(((ListaEjecucion)symbolTable.get("lista_exec")));
		
		out.close();
		file.close();
		
	}catch(IOException ex){
		
		System.out.println("Ruta invalida.");
		System.out.println(ex);
		
	}
};

main returns [ASTNode node]: MAIN LPAREN RPAREN instruction
{
	$node = new MainExec($instruction.node);
};

subrutine returns [ASTNode node]: function {$node = $function.node;} 
| procedure {$node = $procedure.node;};

function returns [ASTNode node]: DATATYPE FUNCTION ID features 
{
	$node = new Subrutine($DATATYPE.text,$FUNCTION.text,$ID.text,
		$features.node.get(0), $features.node.get(1), $features.node.get(2));
};

procedure returns [ASTNode node]: PROCEDURE ID features
{
	$node = new Subrutine("",$PROCEDURE.text,$ID.text,
		$features.node.get(0), $features.node.get(1), $features.node.get(2));
};

features returns [List<List<ASTNode>> node]: LPAREN parameters? RPAREN
{ List<ASTNode> declare = new ArrayList<>(); }
	LCURLY ( declaration {declare.add($declaration.node);} )* RCURLY
	instruction
	{	
		List<ASTNode> parm = new ArrayList<>();
		List<ASTNode> instruc = new ArrayList<>();
		
		if ($parameters.node != null){
			parm = $parameters.node;
		}
		
		instruc = $instruction.node;
		
		List<List<ASTNode>> body = new ArrayList<>();
		body.add(parm);
		body.add(declare);
		body.add(instruc);
		$node = body;
	};

parameters returns [List<ASTNode> node]: {List<ASTNode> param = new ArrayList<>();}
 IOTYPE DATATYPE t1=ID 
 {
 	Parameter x = new Parameter($IOTYPE.text,$DATATYPE.text,$t1.text);
 	param.add(x);
 }
 (COMMA IOTYPE DATATYPE t2=ID {
 	Parameter y = new Parameter($IOTYPE.text,$DATATYPE.text,$t2.text);
 	param.add(y);
 })*
{
	$node = param;
};

declaration returns [ASTNode node]: {List<ASTNode> ids = new ArrayList<>();}
DATATYPE t1=idorvector {ids.add($t1.node);}
 (COMMA t2=idorvector {ids.add($t2.node);})* SEMICOLON
 {
 	$node = new Declaration($DATATYPE.text,ids);
 };

idorvector returns [ASTNode node]: d2=ID {$node = new Constant($d2.text,"");}
			| d3=ID LSQUARE t0=NUMBER RSQUARE {$node = new Constant($d3.text+"["+$t0.text+"]","");}
			| d4=ID LSQUARE t1=NUMBER RSQUARE LSQUARE t2=NUMBER RSQUARE {$node = new Constant($d4.text+"["+$t1.text+"]"+"["+$t2.text+"]","");}
			| d5=ID LSQUARE d1=ID RSQUARE {$node = new Constant($d5.text+"["+$d1.text+"]","");};

instruction returns [List<ASTNode> node]: LCURLY {List<ASTNode> body = new ArrayList<>();}
 (expression {body.add($expression.node);})* RCURLY {$node = body;};

expression returns [ASTNode node]: assignation SEMICOLON {$node = $assignation.node;}
 | structure {$node = $structure.node;}
 | subrutinecall SEMICOLON {$node = $subrutinecall.node;} 
 | returnG SEMICOLON {$node = $returnG.node;}
 | declaration {$node = $declaration.node;};

assignation returns [ASTNode node]: idorvector ASSIGN (
	operation {$node = new Assign($idorvector.node,$operation.node);}
	| list {$node = new Assign($idorvector.node,$list.node);}
);

condition returns [ASTNode node]: c1=condition OR t1=condition1 
{$node = new Condition($c1.node,$t1.node,$OR.text);}
		| t2=condition1 {$node = $t2.node;};

condition1 returns [ASTNode node]: c1=condition1 AND t1=condition2
{$node = new Condition($c1.node,$t1.node,$AND.text);}
		| t2=condition2 {$node = $t2.node;};
		
condition2 returns [ASTNode node]: s1=condition2 EQUALS t1=condition3
{$node = new Condition($s1.node,$t1.node,$EQUALS.text);}
		| s2=condition2 ODD t2=condition3
{$node = new Condition($s2.node,$t2.node,$ODD.text);}
		| t3=condition3 {$node = $t3.node;};
		
condition3 returns [ASTNode node]: c1=condition3 COMP t1=operation 
{$node = new Condition($c1.node,$t1.node,$COMP.text);}
		| t2=operation {$node = $t2.node;}
		| LPAREN condition RPAREN {$node = $condition.node;};
		
operation returns [ASTNode node]: t1=operation {$node = $t1.node;}
 PLUS s1=operation1 {$node = new Evaluation($node, $s1.node, $PLUS.text);}
 | t2=operation {$node = $t2.node;} 
 MINUS s2=operation1 {$node = new Evaluation($node, $s2.node, $MINUS.text);}
 | s3=operation1 {$node = $s3.node;};

operation1 returns [ASTNode node]: t1=operation1 {$node = $t1.node;}
 TIMES operation2 {$node = new Evaluation($node, $operation2.node, $TIMES.text);}
 | t2=operation1 {$node = $t2.node;}
 DIVIDE operation2 {$node = new Evaluation($node, $operation2.node, $DIVIDE.text);}
 | operation2 {$node = $operation2.node;};
		
operation2 returns [ASTNode node]: idorvector {$node = new VarRef($idorvector.node);}
		| NEGATE t1=operation2 {$node = new Negacion($t1.node);}
		| data_auxiliar {$node = $data_auxiliar.node;}
		| LPAREN operation RPAREN {$node = $operation.node;}
		| subrutinecall {$node = $subrutinecall.node;};
		
data_auxiliar returns [ASTNode node]: NUMBER {$node = new Constant(Integer.parseInt($NUMBER.text),"");}
 | BOOL {$node = new Constant(Boolean.parseBoolean($BOOL.text),"");} 
 | MINUS NUMBER {$node = new Constant(Integer.parseInt($NUMBER.text),"-");}
 | STR {$node = new Constant($STR.text,"str");};

structure returns [ASTNode node]: ifG {$node = $ifG.node;}
| whileG {$node = $whileG.node;}
| forG {$node = $forG.node;}
| switchG {$node = $switchG.node;}
| repeat {$node = $repeat.node;};

subrutinecall returns [ASTNode node]: t1=ID LPAREN ss1=arguments? RPAREN {$node = new SubRutExec($t1.text,$ss1.node);}
| t2=ID POINT t3=ID LPAREN ss2=arguments? RPAREN {$node = new SubRutExec($t2.text+"."+$t3.text,$ss2.node);};

returnG returns [ASTNode node]: RETURN operation {$node = new Retorno($operation.node);};
			
arguments returns [List<ASTNode> node]: {
	List<ASTNode> args = new ArrayList<>();
}
t1=operation {args.add($t1.node);} (COMMA t2=operation {args.add($t2.node);})*
{
	$node = args;
};

list returns [ASTNode node]: {
	List<ASTNode> components = new ArrayList<>();
}
LCURLY (t1=data_auxiliar {components.add($t1.node);}
 (COMMA t2=data_auxiliar {components.add($t2.node);})*)? RCURLY
 {
 	$node = new NodeList(components);
 };

ifG returns [ASTNode node]: IF condition t1=instruction 
 {
 	List<ASTNode> body = new ArrayList<>();
 	body = $t1.node;
 	List<ASTNode> elseBody = new ArrayList<>();
 }
 (ELSE t2=instruction {elseBody = $t2.node;})?
 {
 	$node = new Conditional($condition.node,body,elseBody);
 };
		
whileG returns [ASTNode node]: WHILE condition instruction
{
	List<ASTNode> body = new ArrayList<>();
	body = $instruction.node;
	$node = new While($condition.node,body); 
};

forG returns [ASTNode node]: FOR LPAREN assignation for1 RPAREN instruction
{
	List<ASTNode> body = new ArrayList<>();
	body = $instruction.node;
	$node = new For($assignation.node,$for1.node[0],$for1.node[1],$for1.node[2],body);	
};

for1 returns [ASTNode[] node]: TO t1=operation INC t2=operation
{
	ASTNode[] x = {$t1.node,$t2.node,new Constant("asc","")};
	$node = x;
}
	| TO t3=operation
{
	ASTNode[] y = {$t3.node,new Constant(1,""),new Constant("asc","")};
	$node = y;
}
	| DOWNTO t4=operation DEC t5=operation
{
	ASTNode[] z = {$t4.node,$t5.node,new Constant("dec","")};
	$node = z;
}
	| DOWNTO t6=operation
{
	ASTNode[] w = {$t6.node,new Constant(1,""),new Constant("dec","")};
	$node = w;
};
	
switchG returns [ASTNode node]: SWITCH LPAREN idorvector RPAREN
{ 
	List<Case> cases = new ArrayList<>();
	List<ASTNode> defaultCase = new ArrayList<>();
}
 LCURLY ((switch1 {cases.add($switch1.node);})* 
 	DEFAULT COLON (t1=expression {defaultCase.add($t1.node);})*
 )? RCURLY
{
	$node = new VarRef($idorvector.node);
	$node = new Switch($node,cases,defaultCase);
};
switch1 returns [Case node]: switch2 COLON
{
	List<ASTNode> body = new ArrayList<>();
} 
(t2=expression {body.add($t2.node);})* BREAK SEMICOLON
{
	$node = new Case($switch2.node, body);
};
switch2 returns [ASTNode node]: NUMBER { $node = new Constant(Integer.parseInt($NUMBER.text),"");} 
| STR { $node = new Constant($STR.text,"str");};

repeat returns [ASTNode node]: REPEAT instruction UNTIL condition SEMICOLON
{
	List<ASTNode> body = new ArrayList<>();
	body = $instruction.node;
	$node = new Repeat($condition.node,body);
};

MAIN: 'main';
FUNCTION: 'function';
PROCEDURE: 'procedure';
RETURN: 'return';

IOTYPE: 'in' | 'out' | 'inout';

OR: 'or';
AND: 'and';

DATATYPE: 'int' | 'char' | 'float' |
 'boolean' | 'string' | 'list';

BOOL: 'true' | 'false';

IF: 'if';
ELSE: 'else';
WHILE: 'while';
FOR: 'for';
SWITCH: 'switch';
BREAK: 'break';
DEFAULT: 'default';
INC: 'inc';
DEC: 'dec';
TO: 'to';
DOWNTO: 'downto';
REPEAT: 'repeat';
UNTIL: 'until';

PLUS: '+';
MINUS: '-';
TIMES: '*';
DIVIDE: '/';

EQUALS: '=';
ODD: '<>';
COMP: '<' | '>' | '<=' | '>=';

NEGATE: '!';

POINT: '.';
COMMA: ',';
COLON: ':';
SEMICOLON: ';';

LPAREN: '(';
RPAREN: ')';
LCURLY: '{';
RCURLY: '}';
LSQUARE: '[';
RSQUARE: ']';

ASSIGN: '<-';

COMMENT: [#][a-zA-Z_0-9/\-\.,;{}\[\] ()\r\t\n=<>\+-\*/%\"]*[#] -> skip;
STR: ["][a-zA-Z_0-9/\-\.,; ]*["];
ID: [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER: [0-9]+;

WS: [ \t\n\r]+ -> skip;
