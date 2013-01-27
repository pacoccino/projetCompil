
public class Variable {
	public String nom;
	public Types type;
	public String className;
	public boolean constant;
	public Object valeur;
	
	public Variable(Types type){
		this.type = type;
	}
	
	public Variable(){
		this.type = Types.OBJECT;
	}
	
	public String ll_typeName() {
		switch(type) {
		case INT:
			return "i32";
		case FLOAT:
			return "double";
		case BOOLEAN:
			return "i1";
		case STRING:
			return "i8*";
		default:
			return "Bad_Type";
		}
	}
}
