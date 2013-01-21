import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class LLVM {
	File destination;
	FileWriter destinationW;
	final static String destinationName="ll.ll";
	
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
