package project;

public class HandTooBigException extends Exception{
	public HandTooBigException(){
		super("Hand is too big to perform the required action");
	}
	
	public HandTooBigException(String s){
		super(s);
	}
}
