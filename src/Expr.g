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
          (ELSE NEWLINE {output.if_else();} stmts END {output.if_else_end();}    
          | END {output.if_end();})                     
        | WHILE WS {output.while_cond();} expr WS DO NEWLINE {output.while_in($expr.identifier);} 
          stmts END {output.while_out();}
        | FOR WS ID WS IN WS a=expr WS TO WS b=expr WS  { output.for_in($ID.text, $a.identifier, $b.identifier); }
          DO NEWLINE stmts END { output.for_out($ID.text); }
        | ID (WS)* '=' (WS)* expr       { output.store($ID.text, $expr.identifier);  }
        | RETURN WS expr    { output.returnExpr($expr.identifier);}
        | 'print(' ID ')'   { output.print($ID.text); }
      //| DEF ID opt_params term stmts terms END
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
    :   a=INT     { $identifier = $a.text; }
    |   a=FLOAT   { $identifier = $a.text; }
    |   a=STRING  { $identifier = $a.text; }
    |   a=BOOL    { $identifier = $a.text; }
    |   a=ID      { $identifier = output.load($ID.text); }
    ;

terms:
     term+
    ;
term      
    : ';'
    //| WS* '//' (options{greedy=false;} : STRING)* NEWLINE // commentaires
    | NEWLINE
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
