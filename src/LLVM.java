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
	String stackName;
	
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
	}
	
	void putCode(String s) {
		code.append(s+"\n");
	}
	
	public void storeFromInt(String name, int val) {
		if(!varMap.containsKey(name)) {
			putCode("%"+name+" = alloca i32");
		}
		putCode("store i32 "+val+",i32* %"+name);
		varMap.put(name, val);
		
	}
	
	public void storeFrom(String name, String from) {
		
		if(varMap.containsKey(from)) {
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
		varMap.put(stackName, -1);
		return stackName;
	}
	
	private void addStack() {
		stackName = "%t"+nStack;
		nStack++;
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
