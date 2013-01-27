
public class Variable {
	public String nom;
	public Types type;
	public String className;
	public boolean constant;
	public Object valeur;
	
	public Variable(Types type){
		this.type = type;
		constant = false;
	}
	
	public Variable(){
		this(Types.OBJECT);
	}
	
	public String ll_typeName() {
		return LLVM.ll_typeName(type);
	}
}
