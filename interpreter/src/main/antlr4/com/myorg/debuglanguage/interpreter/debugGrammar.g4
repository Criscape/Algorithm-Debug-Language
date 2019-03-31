grammar debugGrammar;

start
:
	'hello' 'world'
;

WS
:
	[ \t\r\n]+ -> skip
;