import java.io.File;

import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import sun.net.www.protocol.http.AuthCacheValue.Type;

public class LLVM {
	File destination;
	FileWriter destinationW;
	final static String destinationName="ll.ll";
	Map<String, Variable> varMap = new HashMap<String, Variable>();
	StringBuffer code;
	int nStack;
	int brStack;
	int loopStack;
	String stackName;
	String brNameT;
	String brNameF;
	String brNameE;
	String loopCondition;
	String loopInstructions;
	String loopContinuation;

	public LLVM() {
		try {
			destination = new File(destinationName);
			destinationW = new FileWriter(destination);
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
		code = new StringBuffer();
		putCode("declare void @print(i32)");
		putCode("define i32 @main() {");
		nStack = 0;
		brStack = 0;
		loopStack = 0;
	}

	void putCode(String s) {
		code.append(s+"\n");
	}

	public void store(String name, String val) {
		Types type = getType(name);
		if(!varMap.containsKey(name)) {
			putCode("%"+name+" = alloca i32");
		}
		putCode("store i32 "+val+",i32* %"+name);
		varMap.put(name, new Variable(type));
	}
	
	private Types getType(String code) {
		final String INT = "[0-9]+";
		final String FLOAT = "[0-9]+\\.[0-9]*";
		final String ID  = "[\\$@]?[a-zA-Z_]+[a-zA-Z0-9_]*";
		final String BOOL  = "true|false";
		final String STRING  = "\".*\"";
		if(code.matches("%" + ID)) {
			System.out.println("variable");
			return Types.OBJECT;
		}
		else if(code.matches(INT)){
			return Types.INT;
		}
		else if(code.matches(FLOAT)){
			return Types.FLOAT;
		}
		else if(code.matches(BOOL)){
			return Types.BOOLEAN;
		}
		else if(code.matches(ID)){
			return Types.INT;
		}
		else if(code.matches(STRING)){
			return Types.STRING;
		}
		
		return Types.OBJECT;
	}
	
	public void printType(String s) {
		System.out.println(getType(s).toString());
	}

	public String condition(String aName, String bName, String cond) {
		addStack();
		String compItem = null;
		if (cond.equals("=="))
			compItem = new String("eq");
		else  if (cond.equals("!="))
			compItem = new String("ne");
		else  if (cond.equals("<="))
			compItem = new String("use");
		else  if (cond.equals(">="))
			compItem = new String("sge");
		else  if (cond.equals(">"))
			compItem = new String("sgt");
		else  if (cond.equals("<"))
			compItem = new String("slt");
		else
			error("opérateur non prit en charge");
		putCode(stackName + " = icmp " + compItem + " i32 " + aName + ", " + bName);
		return stackName;
	}
	
	
	public void while_cond() {
		addLoop();
		putCode("br label %" + loopCondition);
		putCode(loopCondition + ":");
	}
	
	public void while_in(String cName) {
		putCode("br i1 "+ cName + ", label %" + loopInstructions + ", label %" + loopContinuation);
		putCode(loopInstructions + ":");
	}
	
	public void returnExpr(String cName) {
		putCode("ret i32 " + cName);
	}
	
	public void while_out(String cName) {
		putCode("br label %" + loopCondition);
		putCode(loopContinuation + ":");
	}
	
	public void if_in(String cName) {
		addBr();
		putCode("br i1 "+ cName + ", label %" + brNameT + ", label %" +brNameF);
		putCode(brNameT + ":");
		
	}
	
	public void if_else() {
		putCode("br label %"+brNameE);
		putCode(brNameF + ":");
		
	}
	public void if_else_end() {
		putCode("br label %"+brNameE);
		putCode(brNameE + ":");
		
	}
	public void if_end() {
		putCode("br label %"+brNameF);
		putCode("br label %"+brNameF);
		putCode(brNameF + ":");
		
	}

	
	
	public void storeFrom(String name, String from) {
		
		if(from.charAt(0)!='%' || varMap.containsKey(from)) {
			if(!varMap.containsKey(name)) {
				putCode("%"+name+" = alloca i32");
			}
			putCode("store i32 "+from+",i32* %"+name);
			varMap.put(name, varMap.get(from));
		}
	}

	public String load(String name) {
		addStack();
		if(varMap.containsKey(name)) {
			putCode(stackName+" = load i32* %"+name+"");
		}
		else 
			error("unknown variable "+name);
		return stackName;
	}

	public void print(String name) {
		putCode("call void @print( i32 "+ load(name) +" )");
		System.out.println("out:"+name);
	}

	public String addition(String a, String b){
		addStack();
		Variable newVar = new Variable();
		if(getType(a) == Types.INT && getType(b) == Types.INT)
		{
			newVar.type = Types.INT;
		}
		else if(getType(a) == Types.FLOAT && getType(b) == Types.FLOAT)
		{
			newVar.type = Types.INT;
		}
		putCode(stackName+" = add "+ newVar.ll_typeName() +" "+a+", "+b);
		varMap.put(stackName.substring(1),newVar);
		return stackName;
	}
	
	public String substract(String a, String b){
		addStack();
		Types type = Types.OBJECT;
		putCode(stackName+" = sub i32 "+a+", "+b);
		varMap.put(stackName.substring(1), new Variable(type));
		return stackName;
	}
	
	public String multiply(String a, String b){
		addStack();
		Types type = Types.OBJECT;
		putCode(stackName+" = mul i32 "+a+", "+b);
		varMap.put(stackName.substring(1), new Variable(type));
		return stackName;
	}
	
	public String division(String a, String b){
		addStack();
		Types type = Types.OBJECT;
		putCode(stackName+" = mul i32 "+a+", "+b);
		varMap.put(stackName.substring(1), new Variable(type));
		return stackName;
	}

	private void addStack() {
		stackName = "%t"+nStack;
		nStack++;
	}

	private void addBr() {
		brNameT = "ifT" + brStack;
		brNameF = "ifF" + brStack;
		brNameE = "ifE" + brStack;
		brStack++;
	}
	
	private void addLoop() {
		loopCondition = "loopCond" + loopStack;
		loopInstructions = "loopInstructs" + loopStack;
		loopContinuation = "LoopContinu" + loopStack;
		loopStack++;
	}

	private void error(String err) {
		System.out.println("Erreur de parcours :\n\t"+err+"\n");
	}

	/*public void setStack() {
		stackSnapshot=tempStack;
	}
	public void endStack() {

		tempStack=stackSnapshot;
	}*/

	public void finalize(){
		for(String s:varMap.keySet())
			System.out.println(s);
		putCode("ret i32 0");
		putCode("}");
		String finalCode = new String(code);
		try{
			destinationW.write(finalCode);
			destinationW.flush();
			destinationW.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
	}
}
