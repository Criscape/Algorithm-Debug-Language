grammar debugGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
	import java.util.List;
	import java.util.ArrayList;
	import com.myorg.debuglanguage.interpreter.ast.*;
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<>();
}

program: subrutine* | main;

main returns [ASTNode node]: MAIN LPAREN main_parameters? RPAREN instruction {$node = $instruction.node;};
main_parameters: DATATYPE ID COMMA DATATYPE LSQUARE RSQUARE ID;

subrutine: function | procedure;

function: DATATYPE FUNCTION ID features;
procedure: PROCEDURE ID features;

features: LPAREN parameters? RPAREN
	LCURLY declaration* RCURLY
	instruction;

parameters: IOTYPE DATATYPE idorvector (COMMA IOTYPE DATATYPE idorvector)*;

declaration: DATATYPE idorvector (COMMA DATATYPE idorvector)* SEMICOLON;

idorvector returns [ASTNode node]: ID /*{$node = symbolTable.get($ID.text);}*/
			| ID LSQUARE NUMBER RSQUARE 
			| ID LSQUARE NUMBER RSQUARE LSQUARE NUMBER RSQUARE;

instruction returns [ASTNode node]: LCURLY expression* RCURLY;

expression: assignation SEMICOLON | structure | subrutinecall SEMICOLON | returnG SEMICOLON;

assignation: idorvector ASSIGN (operation | list);

condition: condition OR condition1
		| condition1;

condition1: condition1 AND condition2
		| condition2;
		
condition2: condition2 EQUALS condition3
		| condition2 ODD condition3
		| condition3;
		
condition3: condition3 COMP operation 
		| operation
		| LPAREN condition RPAREN;

operation returns [ASTNode node]: t1=operation {$node = $t1.node;}
 PLUS operation1 {$node = new Evaluation($node, $operation1.node, $PLUS.text);}
 | t2=operation {$node = $t2.node;} 
 MINUS operation1 {$node = new Evaluation($node, $operation1.node, $MINUS.text);}
 | operation1 {$node = $operation1.node;};

operation1 returns [ASTNode node]: t1=operation1 {$node = $t1.node;}
 TIMES operation2 {$node = new Evaluation($node, $operation2.node, $TIMES.text);}
 | t2=operation1 {$node = $t2.node;}
 DIVIDE operation2 {$node = new Evaluation($node, $operation2.node, $DIVIDE.text);}
 | operation2 {$node = $operation2.node;};
		
operation2 returns [ASTNode node]: idorvector 
		| NEGATE operation2
		| data_auxiliar
		| LPAREN operation RPAREN
		| subrutinecall;
		
data_auxiliar returns [ASTNode node]: 
		NUMBER {$node = new Constant(Integer.parseInt($NUMBER.text));}
		| BOOL {$node = new Constant(Boolean.parseBoolean($BOOL.text));}
		| MINUS NUMBER {$node = new Constant(Integer.parseInt($NUMBER.text)*-1);} ;

structure: ifG | whileG | forG | switchG | repeat;

subrutinecall: ID LPAREN arguments? RPAREN
{
	if( $ID.text.equals("print") ) System.out.println(symbolTable);
}
| ID POINT subrutinecall;

returnG: RETURN argreturn;
			
arguments: argreturn (COMMA argreturn)*;
argreturn: (STR | operation);

list: LCURLY data_auxiliar (COMMA data_auxiliar)* RCURLY | LCURLY RCURLY;

ifG: IF condition instruction (ELSE instruction)?;
		
whileG: WHILE condition instruction;

forG: FOR assignation for1 instruction;
for1: TO operation INC operation
	| TO operation
	| DOWNTO operation DEC operation
	| DOWNTO operation;
	
switchG: SWITCH switch2 LCURLY (switch1* DEFAULT COLON expression*)? RCURLY;
switch1: (NUMBER | STR) COLON expression* BREAK SEMICOLON;
switch2: idorvector | LPAREN idorvector RPAREN;

repeat: REPEAT instruction UNTIL condition SEMICOLON;

MAIN: 'main';
FUNCTION: 'function';
PROCEDURE: 'procedure';
RETURN: 'return';

/*IN: 'in';
OUT: 'out';
INOUT: 'inout';*/

IOTYPE: 'in' | 'out' | 'inout';

OR: 'or';
AND: 'and';

/*INT: 'int';
CHAR: 'char';
FLOAT: 'float';
LIST: 'list';
BOOLEAN: 'boolean';
STRING: 'string';*/

DATATYPE: 'int' | 'char' | 'float' |
 'boolean' | 'string' | 'list';

/*TRUE: 'true';
FALSE: 'false';*/

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

/*EQUALS: '=';
MINTHAN: '<';
MAXTHAN: '>';
MINETHAN: '<=';
MAXETHAN: '>=';
ODD: '<>';*/

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

COMMENT: [#].*[#] -> skip;
STR: ["][a-zA-Z_0-9/\-\.,;]*["];
ID: [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER: [0-9]+;

WS: [ \t\n\r]+ -> skip;
