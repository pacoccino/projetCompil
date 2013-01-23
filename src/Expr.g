grammar Expr;

options {
  language=Java;
}

tokens {
  IF='if ';
  THEN=' then';
  END='end';
  ELSE='else';
  FOR='for';
  IN='in';
  TO='..';
  WHILE='while';
  DO='do';
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
                
stmt    :
        IF cond THEN NEWLINE stmts
        (ELSE NEWLINE stmts)? END {output.condbr($cond.identifier);}
       // | IF cond THEN NEWLINE stmts ELSE stmts END 
      //| FOR ID IN expr TO expr term stmts terms END
      //| WHILE expr DO term stmts terms END 
        | ID '=' expr      { output.store($ID.text, $expr.identifier);  }
      //| RETURN expr
        | 'print(' ID ')'   { output.print($ID.text); }
      //| DEF ID opt_params term stmts terms END
      ;

cond returns [String identifier]
    :
    a=expr COND b=expr {$identifier = output.condition($a.identifier, $b.identifier, $COND.text);}
    ;
    
expr returns [String identifier]
    :   a=addition  {$identifier = $a.identifier;}
    ;
     
    
addition returns [String identifier]
    :   a=multiplication               { $identifier = $a.identifier; }
    ( '+' b=multiplication { $identifier = output.addition($a.identifier, $b.identifier); } )*
    ;
    
multiplication returns [String identifier]
    :   a=atom               { $identifier = $a.identifier; }
        ( '*' b=atom { $identifier = output.multiply($a.identifier, $b.identifier); } )*
    ;

atom returns [String identifier]
    :   a=INT     { $identifier = $a.text; }
    |   a=ID      { $identifier = output.load($ID.text); }
    ;

terms:
     term+
    ;
term      
    : ';'
    | NEWLINE
    ;


ID  :   ('$'|'@')? ('a'..'z'|'A'..'Z'|'_')+ ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
INT :   '0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t')+ {skip();} ; 
COND : ('<' | '<=' | '>' | '>=' | '==' | '!=');
FLOAT:  ('0'..'9')+ '.' ('0'..'9')* ;
STRING: '\"' (ID|'\n')* '\"';
