
public class Function {
	StringBuffer code;
	Types returnType;
	String name;
	
	public String getName() {
		return name;
	}

	public Types getType() {
		return returnType;
	}
	
	public String getllType() {
		return LLVM.ll_typeName(returnType);
	}

	public boolean setType(Types returnType) {
		if(this.returnType == null) {
			this.returnType = returnType;
			return true;
		}
		else if(this.returnType == returnType)
			return true;
		else
			return false;
	}


	
	public Function(String name) {
		code = new StringBuffer();
		returnType = Types.OBJECT;
		this.name=name;
		this.returnType = null;
	}

	void put(String s) {
			code.append("\t"+s+"\n");
	}
	
	public String getFullCode()
	{
		if(this.returnType == null) 
		{
			this.returnType = Types.VOID;
			code.append("ret void\n");
		}
		else
			code.append("ret "+getllType()+" "+getDeflt()+"\n");
		
		code.insert(0, "define "+getllType()+" @"+name+"() {\n");
		
		code.append("}\n");
		return new String(code);
	}

	private String getDeflt() {
		switch(returnType) {
		case INT:
			return "0";
		case FLOAT:
			return "0.0";
		case BOOLEAN:
			return "0";
		default:
			return "Bad_Type";
		}
	}
}
