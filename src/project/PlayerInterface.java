package project;

public interface PlayerInterface extends DealerInterface{
	
	public void split(Hand h, Shoe s) throws IllegalHandException,NotEnoughMoneyException;
	
	public void insurance(Hand dealerHand) throws IllegalHandException;
	
	public float surrender(Hand dealerHand) throws IllegalHandException;
	
	public boolean doubleBet() throws IllegalHandException, NotEnoughMoneyException;
	
	public double getPlayerMoney();
	
	public void addPlayerMoney(float playerMoney) throws NotEnoughMoneyException;
	
	public Hand getNextHand();
	
	/**
	 * 
	 * @return
	 */
	public Hand getCurrHand();
	
}
