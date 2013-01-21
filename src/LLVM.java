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
	
	public LLVM() {
		try {
		destination = new File(destinationName);
		destinationW = new FileWriter(destination);
		destinationW.write("define i32 @main() {\n");
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
	}
	
	public void store(String name, int val) {
		try{
			if(!varMap.containsKey(name)) {
				destinationW.write("%"+name+"_p = alloca i32\n");
			}
			destinationW.write("store i32 "+val+"+,i32* %"+name+"_p\n");
			varMap.put(name, val);
			}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
	
		
	}
	
	public int load(String name) {
		try{
			if(!varMap.containsKey(name)) {
				destinationW.write("%"+name+" = load i32* %"+name+"_p\n");
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
		return varMap.get(name);
	}
	
	public void print(int n) {
		System.out.println("out:"+n);
		
	}
	
	public void output()
	{
	}
	
	public void add(String s) {
		try{
			destinationW.write(s);
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
	}
	
	public void finalize(){
		try{
			destinationW.write("ret i32 0\n");
			destinationW.write("}\n");
			destinationW.flush();
			destinationW.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
	}
}
