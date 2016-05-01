package project;

public class HandNotValidException extends Exception{
	public HandNotValidException(){
		super("hand does not meet requirements for action");
	}
}
