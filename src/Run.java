import org.antlr.runtime.*;
 
public class Run {
    public static void main(String[] args) throws Exception {
        System.out.println("Hi, please put your Rubic code into standard input.");
        
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        
        parser.prog();
        
        parser.output.finalize();
    }
}