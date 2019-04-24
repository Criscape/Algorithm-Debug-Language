# ADL - Lenguaje para el analisis de algoritmos recursivos e iterativos

//Intro
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
 - idorvector **->** ID | ID LSQUARE NUMBER RSQUARE |
	 - ID (LSQUARE NUMBER RSQUARE)*2 | ID LSQUARE ID LSQUARE
 - instruction **->** LCURLY (expression)* RCURLY
 - expression **->** assignation SEMICOLON | structure | subrutinecall SEMICOLON
	 - returnG SEMICOLON | declaration
 - assignation **->** idorvector ASSIGN (operation | list)
