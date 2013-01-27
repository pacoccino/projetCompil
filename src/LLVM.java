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
		putCode("declare void @printi(i32)");
		putCode("declare void @printd(double)");
		putCode("define i32 @main() {");
		nStack = 0;
		brStack = 0;
		loopStack = 0;
	}

	void putCode(String s) {
		code.append(s+"\n");
	}
	
	public String load(String name) {
		addStack();
		Variable newVar = varMap.get(name);
		if(varMap.containsKey(name)) {
			putCode(stackName+" = load "+newVar.ll_typeName()+"* %"+name+"");
			varMap.put(stackName.substring(1), newVar);
		}
		else 
			error("unknown variable "+name);
		return stackName;
	}

	public void store(String name, String val) {
		Variable oldVar = varMap.get(name);
		Variable newVar = new Variable(getType(val));
		if(oldVar == null) {
			if(name.matches("[A-Z].*"))
				newVar.constant = true;
			putCode("%"+name+" = alloca "+newVar.ll_typeName());
			putCode("store "+newVar.ll_typeName()+" "+val+", "+newVar.ll_typeName()+"* %"+name);
			varMap.put(name, newVar);
		}
		else {
			if(oldVar.constant)
				error("Tentative de reafectation sur une variable constante ("+name+")");
			else if(newVar.type != oldVar.type)
				error("Changement de type d'une variable impossible. ("+name +":"+ newVar.type +"/"+ oldVar.type +")");
			else
			{
				putCode("store "+newVar.ll_typeName()+" "+val+", "+newVar.ll_typeName()+"* %"+name);
				varMap.put(name, newVar);
			}
		}
	}
	
	private Types getType(String code) {
		final String INT = "[0-9]+";
		final String FLOAT = "[0-9]+\\.[0-9]*";
		final String ID  = "[\\$@]?[a-zA-Z_]+[a-zA-Z0-9_]*";
		final String BOOL  = "true|false";
		final String STRING  = "\".*\"";
		
		if(code.matches("%" + ID)) {
			return varMap.get(code.substring(1)).type;
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
			if(varMap.containsKey(code))
				return varMap.get(code).type;
			else
				error("Variable indefinie");
		}
		else if(code.matches(STRING)){
			return Types.STRING;
		}
		
		return Types.OBJECT;
	}
	
	public void printType(String s) {
		System.out.println(getType(s).toString());
	}

	
	public String logical(String aName, String bName, String log) {
		addStack();
		Variable newVar = new Variable(Types.BOOLEAN);
		String compItem = null;
		if(getType(aName) != Types.BOOLEAN || getType(bName) != Types.BOOLEAN)
		{
			error("Comparaison logique sur autres variables que booleens impossible ("+aName+log+bName+")");
			error("m:"+getType(aName) + getType(bName));
			return "err";
		}
		if (log.equals("&&"))
			compItem = new String("and");
		else  if (log.equals("||"))
			compItem = new String("or");
		else
			error("opérateur non prit en charge");
		putCode(stackName + " = " + compItem + " i1 " + aName + ", " + bName);
		varMap.put(stackName.substring(1),newVar);
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

	
	public void storeFrom(String to, String from) {
		
		if(from.charAt(0)!='%' || varMap.containsKey(from)) {
			if(!varMap.containsKey(to)) {
				putCode("%"+to+" = alloca i32");
			}
			putCode("store i32 "+from+",i32* %"+to);
			varMap.put(to, varMap.get(from));
		}
	}

	public void print(String name) {
		
		Types type = getType(name);
		switch (type) {
		case INT:
			putCode("call void @printi( i32 "+ load(name) +" )");
			break;
		case FLOAT:
			putCode("call void @printd( double "+ load(name) +" )");
			break;
		default:
			error("Unprintable variable");
		}
		System.out.println("out:"+name+ " : "+type);
	}
	
	public String operation(String a, String b, String op){
		addStack();
		Variable newVar = new Variable();
		String operation;
		String operationType="";
		if(op.equals("+"))
			operation = "add";
		else if(op.equals("-"))
			operation = "sub";
		else if(op.equals("*"))
			operation = "mul";
		else {
			operation = "cmp";
			if(op.equals("=="))
				operationType = "eq";
			else if (op.equals("!="))
				operationType = "ne";
			else if (op.equals("<="))
				operationType = "use";
			else if (op.equals(">="))
				operationType = "sge";
			else if (op.equals(">"))
				operationType = "sgt";
			else if (op.equals("<"))
				operationType = "slt";
			else
				error("Operateur ??");
		}
		String newA = a;
		String newB = b;
		if(getType(a) == Types.INT && getType(b) == Types.INT)
		{
			if(operation.equals("cmp"))
				operation = "i"+operation;
			newVar.type = Types.INT;
		}
		else if(getType(a) == Types.BOOLEAN && getType(b) == Types.BOOLEAN)
		{
			if(operation.equals("cmp"))
				operation = "i"+operation;
			newVar.type = Types.BOOLEAN;
		}
		else if(getType(a) == Types.FLOAT && getType(b) == Types.FLOAT)
		{
			newVar.type = Types.FLOAT;
			operation = "f"+operation;
		}
		else if(getType(a) == Types.INT && getType(b) == Types.FLOAT)
		{
			if(operation.equals("div"))
				error("Division d'un entier par un flottant impossible");
			newVar.type = Types.FLOAT;
			putCode(stackName+" = uitofp i32 "+a+" to double  ");
			newA = stackName;
			addStack();
			operation = "f"+operation;
		}
		else if(getType(a) == Types.FLOAT && getType(b) == Types.INT)
		{
			newVar.type = Types.FLOAT;
			putCode(stackName+" = uitofp i32 "+b+" to double  ");
			newB = stackName;
			addStack();
			operation = "f"+operation;
		}
		else
			error("Operation impossible. ( "+ getType(a) + op + getType(b) +" )");

		putCode(stackName+" = "+operation+" "+operationType+" "+ newVar.ll_typeName() +" "+newA+", "+newB);
		if(operation.substring(1).equals("cmp"))
			newVar.type=Types.BOOLEAN;
		varMap.put(stackName.substring(1),newVar);
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
		System.out.println("Erreur fatale :\n\t"+err+"\n");
		//System.exit(0);
	}

	/*public void setStack() {
		stackSnapshot=tempStack;
	}
	public void endStack() {

		tempStack=stackSnapshot;
	}*/

	public void finalize(){
		for(String s:varMap.keySet())
			System.out.println(s +" : "+ varMap.get(s).type);
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
