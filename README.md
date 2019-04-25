# ADL - Lenguaje para el analisis de algoritmos recursivos e iterativos

## Gramatica implementada
La gramatica aplicada para el desarrollo del lenguaje es la siguiente:

 - program **->** (subrutine)* main
 - main **->** MAIN LPAREN RPAREN instruction
 - subrutine **->** function | procedure
 - function **->** DATATYPE FUNCTION ID features
 - procedure **->** PROCEDURE ID features
 - features **->** LPAREN (parameters)? RPAREN LCURLY (declaration)* RCURLY instruction
 - parameters **->** IOTYPE DATATYPE ID (COMMA IOTYPE DATATYPE ID)*
 - declaration **->** DATATYPE idorvector (COMMA idorvector)* SEMICOLON
 - idorvector **->** ID | ID LSQUARE NUMBER RSQUARE | ID (LSQUARE NUMBER RSQUARE)*2 |
 	- ID LSQUARE ID LSQUARE
 - instruction **->** LCURLY (expression)* RCURLY
 - expression **->** assignation SEMICOLON | structure | subrutinecall SEMICOLON | returnG SEMICOLON |
	 - declaration
 - assignation **->** idorvector ASSIGN (operation | list)
 - condition **->** condition OR condition1 | condition1
 - condition1 **->** condition1 AND condition2 | condition2
 - condition2 **->** condition2 EQUALS condition3 | condition2 ODD condition3 | condition3
 - condition3 **->** condition3 COMP operation | operation | LPAREN condition RPAREN
 - operation **->** operation PLUS operation1 | operation MINUS operation1 | operation|
 - operation1 **->** operation1 TIMES operation2 | operation1 DIVIDE operation2 | operation3
 - operation2 **->** idorvector | NEGATE operation2 | data_auxiliar | LPAREN operation RPAREN | subrutinecall
 - data_auxiliar **->** NUMBER | BOOL | MINUS NUMBER | STR
 - structure **->** ifG | whileG | forG | switchG | repeat
 - subrutinecall **->** ID LPAREN subrutinecall1 RPAREN | ID POINT ID LPAREN subrutinecall1 RPAREN
 - subrutinecall1 **->** arguments | empty
 - returnG **->** RETURN operation
 - arguments **->** operation (COMMA operation)*
 - list **->** LCURLY data_auxiliar (COMMA data_auxiliar)* RCURLY
 - ifG **->** IF LPAREN condition RPAREN instruction ELSE instruction
 - whileG **->** WHILE condition instruction
 - forG **->** FOR LPAREN assignation for1 RPAREN instruction
 - for1 **->** TO operation INC | TO operation | DOWNTO operation INC | DOWNTO operation
 - switchG **->** SWITCH LPAREN idorvector RPAREN LCURLY ((switch1)* DEFAULT COLON (expression)*)? RCURLY
 - switch1 **->** switch2 COLON (expression)* BREAK SEMICOLON
 - switch2 **->** NUMBER | STR
 - repeat **->** REPEAT instruction UNTIL condition SEMICOLON
 
## Lista de tokens
Los tokens que utiliza la gramatica son los siguientes:
  
|  ID | TOKEN | - | ID | TOKEN |
|--------|---------|---|---------|---------|
| MAIN | 'main' | ----------- | FUNCTION | 'function' |
| PROCEDURE | 'procedure' | ----------- | RETURN | 'return' |
| OR | 'or' | ----------- | AND | 'and' |
| IF | 'if' | ----------- | ELSE | 'else' |
| WHILE | 'while' | ----------- | FOR | 'for' |
| SWITCH | 'switch' | ----------- | BREAK | 'break' |
| DEFAULT | 'default' | ----------- | INC | 'inc' |
| DEC | 'dec' | ----------- | TO | 'to' |
| DOWNTO | 'downto' | ----------- | REPEAT | 'repeat' |
| UNTIL | 'until' | ----------- | PLUS | '+' |
| MINUS | '-' | ----------- | TIMES | '*' |
| DIVIDE | '/' | ----------- | EQUALS | '=' |
| ODD | '<>' | ----------- | NEGATE | '!' |
| POINT | '.' | ----------- | COMMA | ',' |
| COLON | ':' | ----------- | SEMICOLON | ';' |
| LPAREN | '(' | ----------- | RPAREN | ')' |
| LCURLY | '{' | ----------- | RCURLY | '}' |
| LSQUARE | '\[' | ----------- | RSQUARE | ']' |
| ASSIGN | '<-' | ----------- |  |  |

Y tokens basados en una expresion regular o agrupaciones:
  
|  ID | TOKEN |
|--------|---------|
| IOTYPE | 'in' \| 'out' \| 'inout' |
| DATATYPE | 'int' \| 'char' \| 'float' \| 'boolean' \| 'string' \| 'list' |
| BOOL | 'true' \| 'false' |
| COMP | '<' \| '>' \| '<=' \| '>=' |
| COMMENT | \[#] \[a-zA-Z_0-9/\-\.,;{}\[\] ()\r\t\n=<>\+-\*/%\"]* \[#] |
| STR | \["] \[a-zA-Z_0-9/\-\.,; ]* \["] |
| ID | \[a-zA-Z_]\[a-zA-Z0-9_]* |
| NUMBER | \[0-9]+ |
| WS | \[ \t\n\r] |
