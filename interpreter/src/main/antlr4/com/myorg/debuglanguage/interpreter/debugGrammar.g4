grammar debugGrammar;

@parser::header {
	import java.util.Map;
	import java.util.HashMap;
}

@parser::members {
	Map<String, Object> symbolTable = new HashMap<>();
}

program: subrutine* main;

main: MAIN LPAREN main_parameters? RPAREN instruction;
main_parameters: DATATYPE ID COMMA
 DATATYPE LSQUARE RSQUARE ID;

subrutine: function | procedure;

function: DATATYPE FUNCTION ID features;
procedure: PROCEDURE ID features;

features: LPAREN parameters? RPAREN
	LCURLY declaration* RCURLY
	instruction;

parameters: IOTYPE DATATYPE idorvector parameters1*;
parameters1: COMMA parameters;

declaration: DATATYPE idorvector declaration1* SEMICOLON;
declaration1: COMMA idorvector;

idorvector: ID
			| ID LSQUARE NUMBER? RSQUARE
			| ID LSQUARE NUMBER? RSQUARE LSQUARE NUMBER? RSQUARE;

instruction: LCURLY expression* RCURLY;

expression: assignation SEMICOLON | structure | subrutinecall SEMICOLON | returnG SEMICOLON;

assignation: idorvector ASSIGN assignation1;

assignation1: operation | list;

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

operation: operation PLUS operation1
		| operation MINUS operation1
		| operation1;

operation1: operation1 TIMES operation2
		| operation1 DIVIDE operation2
		| operation2;
		
operation2: idorvector
		| NEGATE operation2
		| data_auxiliar
		| LPAREN operation RPAREN
		| subrutinecall;
		
data_auxiliar: NUMBER | BOOL | MINUS NUMBER;

structure: ifG | whileG | forG | switchG | repeat;

subrutinecall: ID LPAREN arguments? RPAREN
			| ID POINT subrutinecall;

returnG: RETURN arguments2;
			
arguments: arguments2 arguments1*;
arguments1: COMMA arguments2;
arguments2: STR | operation;

list: LCURLY data_auxiliar list1* RCURLY | LCURLY RCURLY;
list1: COMMA data_auxiliar;

ifG: IF condition instruction if1?;
if1: ELSE instruction;
		
whileG: WHILE condition instruction;

forG: FOR assignation for1 instruction;
for1: TO operation INC operation
	| TO operation
	| DOWNTO operation DEC operation
	| DOWNTO operation;
	
switchG: SWITCH switch4 LCURLY switch1? RCURLY;
switch1: switch2* DEFAULT COLON expression*;
switch2: switch3 COLON expression* BREAK SEMICOLON;
switch3: NUMBER | STR;
switch4: idorvector | LPAREN idorvector RPAREN;

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
