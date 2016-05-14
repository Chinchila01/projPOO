package project;

public class NotEnoughMoneyException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughMoneyException(){
		super("Not enough money");
	}
}
