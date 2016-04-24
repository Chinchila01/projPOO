package project;

import java.util.ListIterator;

public class Player implements PlayerInterface{

	
	private int playerMoney;
	
	private Hand[] hand;
	private short nrHands;
	int minBet;
	/**
	 * Constructor for a Player object. Needs a object player to be created.
	 * 
	 * @param Object player
	 * @see Player Constructor
	 */
	public Player(int initialMoney, int minBet){
		playerMoney=initialMoney;
		hand[0]=new Hand(null, null, minBet);//depois o dealer é que vai dar as cartas
		nrHands = 1;
		this.minBet=minBet;
	}
	
	/**
	 * Implementation of inherited method hit
	 * Gets the next card from Shoe
	 * 
	 * @param Shoe
	 * @return Card object
	 * @see hit
	 */
	public Card hit(Shoe s){
		return s.getNext();
	}
	
	/**
	 * Implementation of inherited method stand
	 * This move is supposed to not get any further card and stick with the actual hand
	 * against the Dealer's Hand
	 * 
	 * @see stand
	 */
	public void stand(){
		
		
	}
	
	/**
	 * When you get two starting cards of the same face value, you have the option to split the hand 
	 * in two. You place another bet of the same size as the original bet and play on with two hands.
	 * 
	 * @param Hand h to be split 
	 * @param Shoe s, from which the new card will be picked through hit method
	 * 
	 * 
	 * @see 
	 */
	public void split(Hand h, Shoe s){
		
		if(h.getSize() != 2);//TODO: Add exception
		if(h.getHand().iterator().next().getScore()>=10);//TODO: throw exception
			
		ListIterator<Card> iterator = h.getHand().listIterator();
		Card aux = iterator.next();
		if(aux.equals(iterator.next())){
			iterator.remove();
		}
		
		h.addCard(this.hit(s));//get a card from shoe
		hand[nrHands-1+1] = new Hand(aux, this.hit(s), minBet);//create a new hand 
		
	}
	
	/**
	 * When the dealer’s face-up card is an ace, each player gets the chance to bet on whether the
	 * dealer has a blackjack. 
	 * @param Hand dh -  dealer's hand
	 * @return insurance bet value
	 * 
	 * @see 
	 */
	public int insurance(Hand dh, Hand ph){
		
		//only check the card that is initially faced up
		if (dh.getHand().iterator().next().getType()=='A')
			return ph.bet;//insurance bet 
		else
			;//TODO:throw a exception
		return 0;
	
	}
	
	/**
	 * 
	 * @param
	 * @return
	 * 
	 * @see 
	 */
	public void surrender(){
		
		
	}
	
	/**
	 * 
	 * @param
	 * @return
	 * 
	 * @see 
	 */
	public void getCurrentBet(){
		
		
	}

	public int getPlayerMoney() {
		return playerMoney;
	}

	public void addPlayerMoney(int playerMoney) {
		this.playerMoney = this.playerMoney + playerMoney;
	}
}
