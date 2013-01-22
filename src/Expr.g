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
                
stat:  
        ID '=' INT NEWLINE   { output.storeFromInt($ID.text, Integer.parseInt($INT.text)); } 
    |   ID '=' expr NEWLINE  { output.storeFrom($ID.text, $expr.identifier);  }
    |   'print(' ID ')' NEWLINE    { output.print($ID.text); }
  //  |   expr NEWLINE
    |   NEWLINE
    ;

expr returns [String identifier]
    :   ID  {$identifier = output.load($ID.text);}
    |   addition  {$identifier=$addition.identifier;}
    ;
    
addition returns [String identifier]
    :   a=atom '+' b=additions { //output.setStack(); 
                                 $identifier = output.addition($a.identifier, $b.identifier); 
                                 //output.endStack();
                                }
    ;
additions returns [String identifier]
    :   a=atom               { $identifier = $a.identifier; }
    |   a=atom '+' b=additions { $identifier = output.addition($a.identifier, $b.identifier); }
    ;

atom returns [String identifier]
    :   a=INT     { $identifier = $a.text; }
    |   a=ID      { $identifier = output.load($ID.text); }
    ;


//atom returns [String value]
 //   :   INT {$value = $INT.text;}
   // |   ID
    //    {
      //  Integer v = output.load($ID.text);
       // if ( v!=null ) $value = new String('\%'+$ID.text);
        //else System.err.println("undefined variable "+$ID.text);
       // }
   // |   '(' expr ')' {$value = $expr.value;}
   // ;//

ID  :   ('a'..'z'|'A'..'Z')+ ;
INT :   '0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  :   (' '|'\t')+ {skip();} ; 
