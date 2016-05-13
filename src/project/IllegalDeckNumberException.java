package project;

public class IllegalDeckNumberException extends Exception {

	public IllegalDeckNumberException(){
		super("number of decks must be between 2 and 8, try again");
	}
}
