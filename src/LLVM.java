import java.io.File;

import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class LLVM {
	File destination;
	FileWriter destinationW;
	final static String destinationName="ll.ll";
	Map<String, Integer> varMap = new HashMap<String, Integer>();
	StringBuffer code;
	int nStack;
	int brStack;
	int loopStack;
	String stackName;
	String brNameT;
	String brNameF;
	String brNameE;
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
		if(!varMap.containsKey(name)) {
			putCode("%"+name+" = alloca i32");
		}
		putCode("store i32 "+val+",i32* %"+name);
		varMap.put(name, 0);
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

	public void uncondbr(String cName) {
		addBr();
		putCode("br i1 "+ cName + ", label %" + brNameT + ", label %" +brNameF);
		putCode("goto %" + brNameF);
		putCode(brNameT + ":");
		putCode(brNameF + ":");
	}
	
	public void condbr(String cName) {
		addBr();
		putCode("br i1 "+ cName + ", label %" + brNameT + ", label %" +brNameF);
		putCode(brNameT + ":");
		putCode(brNameF + ":");
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

	public void	loopwhile(String cName) {
		addLoop();
		putCode("br i1 "+ cName + ", label %" + brNameT + ", label %" +brNameF);
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
		putCode(stackName+" = add i32 "+a+", "+b);
		varMap.put(stackName.substring(1), -1);
		return stackName;
	}
	
	public String multiply(String a, String b){
		addStack();
		putCode(stackName+" = mul i32 "+a+", "+b);
		varMap.put(stackName.substring(1), -1);
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
