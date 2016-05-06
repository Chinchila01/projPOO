package project;

public class NoPlayedRoundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoPlayedRoundsException(){
		super("No rounds were played yet");
	}

}
