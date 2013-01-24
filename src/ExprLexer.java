// $ANTLR 3.4 /home/tony/git/projetCompil/src/Expr.g 2013-01-23 20:08:53

import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class ExprLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int AND=4;
    public static final int COMP=5;
    public static final int DO=6;
    public static final int ELSE=7;
    public static final int END=8;
    public static final int FLOAT=9;
    public static final int FOR=10;
    public static final int ID=11;
    public static final int IF=12;
    public static final int IN=13;
    public static final int INT=14;
    public static final int NEWLINE=15;
    public static final int OR=16;
    public static final int STRING=17;
    public static final int THEN=18;
    public static final int TO=19;
    public static final int WHILE=20;
    public static final int WS=21;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public ExprLexer() {} 
    public ExprLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ExprLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "/home/tony/git/projetCompil/src/Expr.g"; }

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:11:5: ( '&&' )
            // /home/tony/git/projetCompil/src/Expr.g:11:7: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "DO"
    public final void mDO() throws RecognitionException {
        try {
            int _type = DO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:12:4: ( 'do' )
            // /home/tony/git/projetCompil/src/Expr.g:12:6: 'do'
            {
            match("do"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DO"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:13:6: ( 'else' )
            // /home/tony/git/projetCompil/src/Expr.g:13:8: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:14:5: ( 'end' )
            // /home/tony/git/projetCompil/src/Expr.g:14:7: 'end'
            {
            match("end"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:15:5: ( 'for' )
            // /home/tony/git/projetCompil/src/Expr.g:15:7: 'for'
            {
            match("for"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:16:4: ( 'if' )
            // /home/tony/git/projetCompil/src/Expr.g:16:6: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:17:4: ( 'in' )
            // /home/tony/git/projetCompil/src/Expr.g:17:6: 'in'
            {
            match("in"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:18:4: ( '||' )
            // /home/tony/git/projetCompil/src/Expr.g:18:6: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:19:6: ( 'then' )
            // /home/tony/git/projetCompil/src/Expr.g:19:8: 'then'
            {
            match("then"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "TO"
    public final void mTO() throws RecognitionException {
        try {
            int _type = TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:20:4: ( '..' )
            // /home/tony/git/projetCompil/src/Expr.g:20:6: '..'
            {
            match(".."); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TO"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:21:7: ( 'while' )
            // /home/tony/git/projetCompil/src/Expr.g:21:9: 'while'
            {
            match("while"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:22:7: ( ')' )
            // /home/tony/git/projetCompil/src/Expr.g:22:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:23:7: ( '*' )
            // /home/tony/git/projetCompil/src/Expr.g:23:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:24:7: ( '+' )
            // /home/tony/git/projetCompil/src/Expr.g:24:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:25:7: ( ';' )
            // /home/tony/git/projetCompil/src/Expr.g:25:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:26:7: ( '=' )
            // /home/tony/git/projetCompil/src/Expr.g:26:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:27:7: ( 'print(' )
            // /home/tony/git/projetCompil/src/Expr.g:27:9: 'print('
            {
            match("print("); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:90:5: ( ( '$' | '@' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+ ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // /home/tony/git/projetCompil/src/Expr.g:90:9: ( '$' | '@' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+ ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // /home/tony/git/projetCompil/src/Expr.g:90:9: ( '$' | '@' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='$'||LA1_0=='@') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /home/tony/git/projetCompil/src/Expr.g:
                    {
                    if ( input.LA(1)=='$'||input.LA(1)=='@' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // /home/tony/git/projetCompil/src/Expr.g:90:20: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/tony/git/projetCompil/src/Expr.g:
            	    {
            	    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            // /home/tony/git/projetCompil/src/Expr.g:90:45: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/tony/git/projetCompil/src/Expr.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:91:5: ( ( '0' .. '9' )+ )
            // /home/tony/git/projetCompil/src/Expr.g:91:9: ( '0' .. '9' )+
            {
            // /home/tony/git/projetCompil/src/Expr.g:91:9: ( '0' .. '9' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/tony/git/projetCompil/src/Expr.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:92:8: ( ( '\\r' )? '\\n' )
            // /home/tony/git/projetCompil/src/Expr.g:92:9: ( '\\r' )? '\\n'
            {
            // /home/tony/git/projetCompil/src/Expr.g:92:9: ( '\\r' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\r') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/tony/git/projetCompil/src/Expr.g:92:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:93:5: ( ( ' ' | '\\t' )+ )
            // /home/tony/git/projetCompil/src/Expr.g:93:9: ( ' ' | '\\t' )+
            {
            // /home/tony/git/projetCompil/src/Expr.g:93:9: ( ' ' | '\\t' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\t'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/tony/git/projetCompil/src/Expr.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMP"
    public final void mCOMP() throws RecognitionException {
        try {
            int _type = COMP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:94:6: ( ( '<' | '<=' | '>' | '>=' | '==' | '!=' ) )
            // /home/tony/git/projetCompil/src/Expr.g:94:8: ( '<' | '<=' | '>' | '>=' | '==' | '!=' )
            {
            // /home/tony/git/projetCompil/src/Expr.g:94:8: ( '<' | '<=' | '>' | '>=' | '==' | '!=' )
            int alt7=6;
            switch ( input.LA(1) ) {
            case '<':
                {
                int LA7_1 = input.LA(2);

                if ( (LA7_1=='=') ) {
                    alt7=2;
                }
                else {
                    alt7=1;
                }
                }
                break;
            case '>':
                {
                int LA7_2 = input.LA(2);

                if ( (LA7_2=='=') ) {
                    alt7=4;
                }
                else {
                    alt7=3;
                }
                }
                break;
            case '=':
                {
                alt7=5;
                }
                break;
            case '!':
                {
                alt7=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // /home/tony/git/projetCompil/src/Expr.g:94:9: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 2 :
                    // /home/tony/git/projetCompil/src/Expr.g:94:15: '<='
                    {
                    match("<="); 



                    }
                    break;
                case 3 :
                    // /home/tony/git/projetCompil/src/Expr.g:94:22: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 4 :
                    // /home/tony/git/projetCompil/src/Expr.g:94:28: '>='
                    {
                    match(">="); 



                    }
                    break;
                case 5 :
                    // /home/tony/git/projetCompil/src/Expr.g:94:35: '=='
                    {
                    match("=="); 



                    }
                    break;
                case 6 :
                    // /home/tony/git/projetCompil/src/Expr.g:94:42: '!='
                    {
                    match("!="); 



                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMP"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:95:6: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* )
            // /home/tony/git/projetCompil/src/Expr.g:95:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )*
            {
            // /home/tony/git/projetCompil/src/Expr.g:95:9: ( '0' .. '9' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /home/tony/git/projetCompil/src/Expr.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            match('.'); 

            // /home/tony/git/projetCompil/src/Expr.g:95:25: ( '0' .. '9' )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/tony/git/projetCompil/src/Expr.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/tony/git/projetCompil/src/Expr.g:96:7: ( '\\\"' ( ID | '\\n' )* '\\\"' )
            // /home/tony/git/projetCompil/src/Expr.g:96:9: '\\\"' ( ID | '\\n' )* '\\\"'
            {
            match('\"'); 

            // /home/tony/git/projetCompil/src/Expr.g:96:14: ( ID | '\\n' )*
            loop10:
            do {
                int alt10=3;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='$'||(LA10_0 >= '@' && LA10_0 <= 'Z')||LA10_0=='_'||(LA10_0 >= 'a' && LA10_0 <= 'z')) ) {
                    alt10=1;
                }
                else if ( (LA10_0=='\n') ) {
                    alt10=2;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/tony/git/projetCompil/src/Expr.g:96:15: ID
            	    {
            	    mID(); 


            	    }
            	    break;
            	case 2 :
            	    // /home/tony/git/projetCompil/src/Expr.g:96:18: '\\n'
            	    {
            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    public void mTokens() throws RecognitionException {
        // /home/tony/git/projetCompil/src/Expr.g:1:8: ( AND | DO | ELSE | END | FOR | IF | IN | OR | THEN | TO | WHILE | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | ID | INT | NEWLINE | WS | COMP | FLOAT | STRING )
        int alt11=24;
        alt11 = dfa11.predict(input);
        switch (alt11) {
            case 1 :
                // /home/tony/git/projetCompil/src/Expr.g:1:10: AND
                {
                mAND(); 


                }
                break;
            case 2 :
                // /home/tony/git/projetCompil/src/Expr.g:1:14: DO
                {
                mDO(); 


                }
                break;
            case 3 :
                // /home/tony/git/projetCompil/src/Expr.g:1:17: ELSE
                {
                mELSE(); 


                }
                break;
            case 4 :
                // /home/tony/git/projetCompil/src/Expr.g:1:22: END
                {
                mEND(); 


                }
                break;
            case 5 :
                // /home/tony/git/projetCompil/src/Expr.g:1:26: FOR
                {
                mFOR(); 


                }
                break;
            case 6 :
                // /home/tony/git/projetCompil/src/Expr.g:1:30: IF
                {
                mIF(); 


                }
                break;
            case 7 :
                // /home/tony/git/projetCompil/src/Expr.g:1:33: IN
                {
                mIN(); 


                }
                break;
            case 8 :
                // /home/tony/git/projetCompil/src/Expr.g:1:36: OR
                {
                mOR(); 


                }
                break;
            case 9 :
                // /home/tony/git/projetCompil/src/Expr.g:1:39: THEN
                {
                mTHEN(); 


                }
                break;
            case 10 :
                // /home/tony/git/projetCompil/src/Expr.g:1:44: TO
                {
                mTO(); 


                }
                break;
            case 11 :
                // /home/tony/git/projetCompil/src/Expr.g:1:47: WHILE
                {
                mWHILE(); 


                }
                break;
            case 12 :
                // /home/tony/git/projetCompil/src/Expr.g:1:53: T__22
                {
                mT__22(); 


                }
                break;
            case 13 :
                // /home/tony/git/projetCompil/src/Expr.g:1:59: T__23
                {
                mT__23(); 


                }
                break;
            case 14 :
                // /home/tony/git/projetCompil/src/Expr.g:1:65: T__24
                {
                mT__24(); 


                }
                break;
            case 15 :
                // /home/tony/git/projetCompil/src/Expr.g:1:71: T__25
                {
                mT__25(); 


                }
                break;
            case 16 :
                // /home/tony/git/projetCompil/src/Expr.g:1:77: T__26
                {
                mT__26(); 


                }
                break;
            case 17 :
                // /home/tony/git/projetCompil/src/Expr.g:1:83: T__27
                {
                mT__27(); 


                }
                break;
            case 18 :
                // /home/tony/git/projetCompil/src/Expr.g:1:89: ID
                {
                mID(); 


                }
                break;
            case 19 :
                // /home/tony/git/projetCompil/src/Expr.g:1:92: INT
                {
                mINT(); 


                }
                break;
            case 20 :
                // /home/tony/git/projetCompil/src/Expr.g:1:96: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 21 :
                // /home/tony/git/projetCompil/src/Expr.g:1:104: WS
                {
                mWS(); 


                }
                break;
            case 22 :
                // /home/tony/git/projetCompil/src/Expr.g:1:107: COMP
                {
                mCOMP(); 


                }
                break;
            case 23 :
                // /home/tony/git/projetCompil/src/Expr.g:1:112: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 24 :
                // /home/tony/git/projetCompil/src/Expr.g:1:118: STRING
                {
                mSTRING(); 


                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    static final String DFA11_eotS =
        "\2\uffff\4\20\1\uffff\1\20\1\uffff\1\20\4\uffff\1\36\1\20\1\uffff"+
        "\1\40\4\uffff\1\42\3\20\1\46\1\47\2\20\1\uffff\1\20\3\uffff\1\20"+
        "\1\54\1\55\2\uffff\3\20\1\61\2\uffff\1\62\2\20\2\uffff\1\65\1\20"+
        "\2\uffff";
    static final String DFA11_eofS =
        "\67\uffff";
    static final String DFA11_minS =
        "\1\11\1\uffff\1\157\1\154\1\157\1\146\1\uffff\1\150\1\uffff\1\150"+
        "\4\uffff\1\75\1\162\1\uffff\1\56\4\uffff\1\60\1\163\1\144\1\162"+
        "\2\60\1\145\1\151\1\uffff\1\151\3\uffff\1\145\2\60\2\uffff\1\156"+
        "\1\154\1\156\1\60\2\uffff\1\60\1\145\1\164\2\uffff\1\60\1\50\2\uffff";
    static final String DFA11_maxS =
        "\1\174\1\uffff\1\157\1\156\1\157\1\156\1\uffff\1\150\1\uffff\1\150"+
        "\4\uffff\1\75\1\162\1\uffff\1\71\4\uffff\1\172\1\163\1\144\1\162"+
        "\2\172\1\145\1\151\1\uffff\1\151\3\uffff\1\145\2\172\2\uffff\1\156"+
        "\1\154\1\156\1\172\2\uffff\1\172\1\145\1\164\2\uffff\1\172\1\50"+
        "\2\uffff";
    static final String DFA11_acceptS =
        "\1\uffff\1\1\4\uffff\1\10\1\uffff\1\12\1\uffff\1\14\1\15\1\16\1"+
        "\17\2\uffff\1\22\1\uffff\1\24\1\25\1\26\1\30\10\uffff\1\20\1\uffff"+
        "\1\23\1\27\1\2\3\uffff\1\6\1\7\4\uffff\1\4\1\5\3\uffff\1\3\1\11"+
        "\2\uffff\1\13\1\21";
    static final String DFA11_specialS =
        "\67\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\23\1\22\2\uffff\1\22\22\uffff\1\23\1\24\1\25\1\uffff\1\20"+
            "\1\uffff\1\1\2\uffff\1\12\1\13\1\14\2\uffff\1\10\1\uffff\12"+
            "\21\1\uffff\1\15\1\24\1\16\1\24\1\uffff\33\20\4\uffff\1\20\1"+
            "\uffff\3\20\1\2\1\3\1\4\2\20\1\5\6\20\1\17\3\20\1\7\2\20\1\11"+
            "\3\20\1\uffff\1\6",
            "",
            "\1\26",
            "\1\27\1\uffff\1\30",
            "\1\31",
            "\1\32\7\uffff\1\33",
            "",
            "\1\34",
            "",
            "\1\35",
            "",
            "",
            "",
            "",
            "\1\24",
            "\1\37",
            "",
            "\1\41\1\uffff\12\21",
            "",
            "",
            "",
            "",
            "\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\43",
            "\1\44",
            "\1\45",
            "\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\50",
            "\1\51",
            "",
            "\1\52",
            "",
            "",
            "",
            "\1\53",
            "\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "",
            "",
            "\1\56",
            "\1\57",
            "\1\60",
            "\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "",
            "",
            "\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\63",
            "\1\64",
            "",
            "",
            "\12\20\7\uffff\32\20\4\uffff\1\20\1\uffff\32\20",
            "\1\66",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( AND | DO | ELSE | END | FOR | IF | IN | OR | THEN | TO | WHILE | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | ID | INT | NEWLINE | WS | COMP | FLOAT | STRING );";
        }
    }
 

}