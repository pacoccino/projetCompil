import java.io.File;

import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
//import java.io.FileReader;
import java.io.IOException;


public class LLVM {
	File destination;
	FileWriter destinationW;
	final static String destinationName="ll.ll";
	Map<String, Variable> varMap;
	Map<String, Variable> globalVarMap;
	
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
	
	StringBuffer funcCalling;
	
	StringBuffer headerCode;
	StringBuffer mainCode;
	Function actualFunc;

	public LLVM() {
		try {
			destination = new File(destinationName);
			destinationW = new FileWriter(destination);
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
		
		mainCode = new StringBuffer();
		headerCode = new StringBuffer();
		
		funcCalling = null;
		
		globalVarMap = new HashMap<String, Variable>();
		varMap = globalVarMap;
		
		nStack = 0;
		brStack = 0;
		loopStack = 0;
		putOutCode("declare void @printi(i32)");
		putOutCode("declare void @printd(double)");
		putOutCode("declare void @prints(i8*)");
		
		mainCode.append("\ndefine i32 @main() {\n");
	}
	
	public void finalize(){
		//for(String s:varMap.keySet())
		//	System.out.println(s +" : "+ varMap.get(s).type);
		mainCode.append("ret i32 0\n");
		mainCode.append("}\n");
		String finalCode = new String(headerCode.append(mainCode));
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

	void putCode(String s) {
		String tab="\t";
		if(actualFunc == null)
			mainCode.append(tab+s+"\n");
		else
			actualFunc.put(s);
	}
	void putOutCode(String s) {
		headerCode.append(s+"\n");
	}
	
	public void begin_function(String name) {
		if(actualFunc != null) {
			error("You cannot define function inside a function, sorry.");
			return;
		}
		actualFunc = new Function(name);
		varMap = new HashMap<String, Variable>();
		
	}
	public void func_param(String param) {
		if(actualFunc == null) {
			error("Mangez des pommes.");
			return;
		}
		store(param, "0");
		
	}
	
	public void end_function() {
		Variable newFunc = new Variable(actualFunc.getType());
		globalVarMap.put(actualFunc.getName(), newFunc);
		headerCode.append(actualFunc.getFullCode());
		actualFunc = null;
		varMap = globalVarMap;
	}
	
	public String func_call(String name) {
		if(globalVarMap.containsKey(name))
		{
			Variable newVar = globalVarMap.get(name);
			funcCalling = new StringBuffer();
			addStack();	
			funcCalling.append(stackName +" = call "+newVar.ll_typeName()+" @"+name+"(");
			varMap.put(stackName.substring(1), newVar);
			return stackName;
		}
		else
		{
			error("Fonction non definie");
			return "err";
		}
	}
	public void func_call_param(String name) {
		//if(funcCalling != null)
		//	funcCalling.append(" "+ll_typeName(getType(name))+ " " + name + ",");
	}
	public void func_call_param_end() {
		if(funcCalling != null) {
			if(funcCalling.charAt(funcCalling.length()-1) != '(')
					funcCalling.deleteCharAt(funcCalling.length()-1);
			funcCalling.append(")");
			putCode(new String(funcCalling));
			funcCalling = null;
		}
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
		if(newVar.type == Types.STRING) {
			int size = val.length()-1;
			String chaine = val.substring(1, size);
			addStack();
			String internal = "@"+stackName.substring(1);
			putOutCode(internal +" = internal constant ["+size+" x i8] c\""+chaine+"\\00\"");
			addStack();
			putCode(stackName +" = getelementptr ["+size+" x i8]* "+internal+", i32 0,i32 0");
			val = stackName;
		}
	
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
	
	public static String ll_typeName(Types type) {
		switch(type) {
		case INT:
			return "i32";
		case FLOAT:
			return "double";
		case BOOLEAN:
			return "i1";
		case STRING:
			return "i8*";
		case VOID:
			return "void";
		default:
			return "Bad_Type";
		}
	}
	
	Types getType(String code) {
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
		else if(op.equals("/"))
			operation = "div";
		else {
			operation = "cmp";
			if(op.equals("=="))
				operationType = "eq";
			else if (op.equals("!="))
				operationType = "ne";
			else if (op.equals("<="))
				operationType = "sle";
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
	
	public void for_in(String varName, String from, String to) {
		addLoop();
		if(getType(from) != Types.INT || getType(to) != Types.INT) {
			error("For utilisable uniquement avec des entiers.");
			return;
		}
		store(varName, from);
		putCode("br label %" + loopCondition);
		putCode(loopCondition + ":");
		String increm = load(varName);
		String cond = operation(increm, to, "<=");
		putCode("br i1 "+ cond + ", label %" + loopInstructions + ", label %" + loopContinuation);
		putCode(loopInstructions + ":");
	}
	public void for_out(String varName) {
		String increm = load(varName);
		String newVal = operation(increm, "1", "+");
		store(varName, newVal);
		putCode("br label %" + loopCondition);
		putCode(loopContinuation + ":");
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
	
	public void while_out() {
		putCode("br label %" + loopCondition);
		putCode(loopContinuation + ":");
	}
	
	public void returnExpr(String cName) {
		if(actualFunc != null) {
			if(!actualFunc.setType(getType(cName)))
			{
				error("Le type de retour d'une fonction doit etre unique.");
				return;
			}
			putCode("ret " +actualFunc.getllType()+ " " + cName);
		}
		else
			putCode("ret i32 " + cName);
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
			putCode("call void @printi( i32 "+ name +" )");
			break;
		case FLOAT:
			putCode("call void @printd( double "+ name +" )");
			break;
		case STRING:
			putCode("call void @prints( i8* "+ name +" )");
			break;
		default:
			error("Unprintable variable");
		}
		//System.out.println("out:"+name+ " : "+type);
	}
	
	void addStack() {
		stackName = "%t"+nStack;
		nStack++;
	}

	void addBr() {
		brNameT = "ifT" + brStack;
		brNameF = "ifF" + brStack;
		brNameE = "ifE" + brStack;
		brStack++;
	}
	
	void addLoop() {
		loopCondition = "loopCond" + loopStack;
		loopInstructions = "loopInstructs" + loopStack;
		loopContinuation = "LoopContinu" + loopStack;
		loopStack++;
	}

	void error(String err) {
		System.out.println("Erreur :\n\t"+err+"\n");
		//System.exit(0);
	}

}
