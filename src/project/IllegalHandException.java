package project;

public class IllegalHandException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IllegalHandException(){
		super("hand is illegal");
	}
	public IllegalHandException(String s){
		super(s);
	}

}
