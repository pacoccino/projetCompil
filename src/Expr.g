grammar Expr;

options {
	language=Java;
}

tokens {
  IF='if';
  THEN='then';
  END='end';
  ELSE='else';
  FOR='for';
  IN='in';
  TO='..';
  WHILE='while';
  DO='do';
  AND='&&';
  OR='||';
  DEF='def';
  RETURN='return';
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


prog:   stmts ;
    
stmts : (stmt terms) +
      ;
             
stmt    : IF WS expr WS THEN NEWLINE  {output.if_in($expr.identifier);} 
          stmts 
          ( ELSE NEWLINE { output.if_else(); } stmts END {output.if_else_end();}    
          | END          { output.if_end();  } )  
                             
        | WHILE                 {output.while_cond();} 
          WS expr WS DO NEWLINE {output.while_in($expr.identifier);} 
          stmts END             {output.while_out(); }
          
        | FOR WS ID WS IN WS a=expr WS TO WS b=expr WS  { output.for_in($ID.text, $a.identifier, $b.identifier); }
          DO NEWLINE stmts END                          { output.for_out($ID.text); }
          
        | ID (WS)* '=' (WS)* expr      { output.store($ID.text, $expr.identifier);  }
        | RETURN WS expr               { output.returnExpr($expr.identifier);}
        | DEF WS ID                    { output.begin_function($ID.text);}
          opt_params NEWLINE 
          stmts END                    { output.end_function();}
          
        | 'print(' expr ')'              { output.print($expr.identifier); }
      ;
      
opt_params      : /* none */
                | '(' ')'
                | '(' params ')'
;
params          : param (',' param)*
; 

param           : ID                  { output.func_param($ID.text);}
;
    
expr returns [String identifier]
    :   a=boolexpr {$identifier = $a.identifier;}
    ;
        
boolexpr returns [String identifier]
    :   a=compexpr  {$identifier = $a.identifier;}
        ((WS)* c=(AND | OR) (WS)* b=compexpr {$identifier = output.logical($a.identifier, $b.identifier, $c.text); })*
    ;
    
compexpr returns [String identifier]
    :   a=addition {$identifier = $a.identifier;}
        ((WS)* COMP (WS)* b=addition {$identifier = output.operation($a.identifier, $b.identifier, $COMP.text); })* 
    ; 

    
addition returns [String identifier]
    :   a=multiplication               { $identifier = $a.identifier; }
        ((WS)* op=('+'|'-') (WS)* b=multiplication { $identifier = output.operation($identifier, $b.identifier, $op.text); } )*
    ;
    
multiplication returns [String identifier]
    :   ( a=atom | '(' a=expr ')' )           { $identifier = $a.identifier; }
        ((WS)* op=('*'|'/') (WS)* b=atom { $identifier = output.operation($identifier, $b.identifier, $op.text); } )*
        ((WS)* op=('*'|'/') (WS)* '(' b=expr ')' { $identifier = output.operation($identifier, $b.identifier, $op.text); } )*
    ;

atom returns [String identifier]
    :   a=INT           { $identifier = $a.text; }
    |   a=FLOAT         { $identifier = $a.text; }
    |   a=STRING        { $identifier = $a.text; }
    |   a=BOOL          { $identifier = $a.text; }
    |   a=ID            { $identifier = output.func_call($ID.text); }
        ( '(' ex_params ')' | '(' ')'  )
                        { output.func_call_param_end(); }
                        
    |   a=ID            { $identifier = output.load($ID.text); }

    ;
ex_params
    :   a=expr        { output.func_call_param($a.identifier); }
        (',' b=expr   { output.func_call_param($b.identifier); } )* 
    ;

terms:
     term+
    ;
term      
    : (WS)* ';'
    //| WS* '//' (options{greedy=false;} : STRING)* NEWLINE // commentaires
    | (WS)* NEWLINE
    ;

BOOL : ('true' | 'false');
ID  :   ('$'|'@')? ('a'..'z'|'A'..'Z'|'_')+;
INT :   '0'..'9'+ ;
FLOAT :   ('0'..'9')+ '.' ('0'..'9')* ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t')+ ; 
COMP : ('<' | '<=' | '>' | '>=' | '==' | '!=');
STRING: '\"' (.)* '\"';
//CHARAUTH: (options{greedy=false;} : 'a'..'z'|'A'..'Z'|'_'|'-'|'0'..'9'|' '|',')*;
