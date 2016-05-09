package project;

public class NoMoreCmdsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMoreCmdsException() {
		super("command file is out of commands");
	}

}
