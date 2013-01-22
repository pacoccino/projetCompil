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
            { if($expr.isVal) output.store($ID.text, $expr.val); 
              else output.storeFrom($ID.text, $expr.identifier);  }
    |   'print(' ID ')' NEWLINE    { output.print($ID.text); }
    |   expr NEWLINE
    |   NEWLINE
    ;

expr returns [boolean isVal, int val, String identifier]
    :   INT {$isVal = true;  $val = Integer.parseInt($INT.text);}
    |   ID  {$isVal = false; 
             output.load($ID.text);
             $identifier = $ID.text;}  
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
