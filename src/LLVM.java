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
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
	}
	
	public void store(String n, int val) {
		varMap.put(n, val);
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
			destinationW.flush();
			destinationW.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();	
		}
	}
}
