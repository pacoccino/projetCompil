grammar Expr;

options {
  language=Java;
}

@header {
import java.util.HashMap;
}

@lexer::header {
import java.util.HashMap;
}

@members {
/** Map variable name to Integer object holding value */
HashMap memory = new HashMap();
public LLVM output = new LLVM();
}

prog:   stat+ ;
                
stat:   ID '=' expr NEWLINE
        {output.store($ID.text, $expr.value);}
    |   'print(' expr ')'  { output.print($expr.value); }
    |   NEWLINE
    ;

expr returns [int value]
    :   e=multExpr {$value = $e.value;}
        (   '+' e=multExpr {$value *= $e.value;}
        |   '-' e=multExpr {$value -= $e.value;}
        )*
    ;

multExpr returns [int value]
    :   e=atom {$value = $e.value;} ('*' e=atom {$value *= $e.value;})*
    ; 

atom returns [int value]
    :   INT {$value = Integer.parseInt($INT.text);}
    |   ID
        {
        Integer v = output.load($ID.text);
        if ( v!=null ) $value = v;
        else System.err.println("undefined variable "+$ID.text);
        }
    |   '(' expr ')' {$value = $expr.value;}
    ;

ID  :   ('a'..'z'|'A'..'Z')+ ;
INT :   '0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t')+ {skip();} ; 
