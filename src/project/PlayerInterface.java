package project;

public interface PlayerInterface extends DealerInterface{
	
	/**
	 * Split method:
	 * When you get two starting cards of the same face value, you have the option to split the hand 
	 * in two. You place another bet of the same size as the original bet and play on with two hands.
	 * 
	 * @param Hand h to be split 
	 * @param Shoe s, from which the new card will be picked through hit method
	 * @throws IllegalHandException
	 * @throws NotEnoughMoneyException
	 */
	public void split(Hand h, Shoe s) throws IllegalHandException,NotEnoughMoneyException;
	
	/**
	 * Insurance method:
	 * When the dealer's face-up card is an ace, each player gets the chance to bet on whether the
	 * dealer has a blackjack. 
	 * @param dealerHand
	 * @throws IllegalHandException
	 */
	public void insurance(Hand dealerHand) throws IllegalHandException;
	
	/**
	 * Surrender method:
	 * Surrenders a hand, returning half of the value of the bet associated with the player's current hand
	 * @param playerHand
	 * @return value to be bet
	 * @throws IllegalHandException
	 */
	public float surrender(Hand dealerHand) throws IllegalHandException;
	
	/**
	 * Doubling Down method:
	 * If you’re fairly sure that your hand will beat the dealer’s, you can double your original bet. 
	 * You’re sometimes allowed to double down for any amount up to the original bet amount.
	 * @return true if double done with success
	 * @throws IllegalHandException
	 * @throws NotEnoughMoneyException
	 */
	public boolean doubleBet() throws IllegalHandException, NotEnoughMoneyException;
	
	/**
	 * Get player money
	 * @return player's money
	 */
	public double getPlayerMoney();
	
	/**
	 * Adds or removes(in case the value passed is negative) money from player
	 * @param playerMoney
	 * @throws NotEnoughMoneyException
	 */
	public void addPlayerMoney(float playerMoney) throws NotEnoughMoneyException;
	
	/**
	 * Get next player's hand
	 * @return Hand object
	 */
	public Hand getNextHand();
	
	/**
	 * Get player current hand 
	 * @return hand object
	 */
	public Hand getCurrHand();
	
}
