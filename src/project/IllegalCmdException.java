package project;

public class IllegalCmdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IllegalCmdException(){
		super("illegal bet");
	}
	
	public IllegalCmdException(String s){
		super(s);
	}
	
}
