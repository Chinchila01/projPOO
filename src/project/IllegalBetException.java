package project;

public class IllegalBetException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IllegalBetException(){
		super("illegal bet");
	}
	
	public IllegalBetException(String s){
		super(s);
	}
	
}
