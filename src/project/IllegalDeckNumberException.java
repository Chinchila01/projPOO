package project;

public class IllegalDeckNumberException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalDeckNumberException(){
		super("number of decks must be between 2 and 8, try again");
	}
}
