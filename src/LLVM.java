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
		put("declare void @print(i32)");
		put("define i32 @main() {");
	}
	
	void put(String s) {

		code.append(s+"\n");
	
	}
	
	public void store(String name, int val) {
		if(!varMap.containsKey(name)) {
			put("%"+name+"_p = alloca i32");
		}
		put("store i32 "+val+",i32* %"+name+"_p");
		varMap.put(name, val);
		
	}
	
	public void storeFrom(String name, String from) {
		
		if(varMap.containsKey(from)) {
			if(!varMap.containsKey(name)) {
				put("%"+name+"_p = alloca i32");
			}
			put("store i32 %"+from+",i32* %"+name+"_p");
			varMap.put(name, varMap.get(from));
		}
	}
	
	public void load(String name) {
		if(varMap.containsKey(name)) {
			put("%"+name+" = load i32* %"+name+"_p");
		}
		else 
			error("unknown variable");
	}
	
	public void print(String name) {
		load(name);
		put("call void @print( i32 %"+ name +" )");
		System.out.println("out:"+name);
	}
	
	private void error(String err) {
		System.out.println("Erreur de parcours :\n\t"+err+"\n");
	}
	
	public void finalize(){
		put("ret i32 0");
		put("}");
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
