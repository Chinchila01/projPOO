package project;

import java.util.ListIterator;

public class Player implements PlayerInterface{

	Player player1;
	private int playerMoney;
	private Hand[] hand;
	private short nrHands;
	/**
	 * Constructor for a Player object. Needs a object player to be created.
	 * 
	 * @param Object player
	 * @see Player Constructor
	 */
	public Player(Player p, int initialMoney){
		player1=p;
		playerMoney=initialMoney;
		hand[0]=new Hand(null, null);//depois o dealer é que vai dar as cartas
		nrHands = 1;
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
	 * @return new Hand
	 * 
	 * @see 
	 */
	public Hand split(Shoe s){
		
		if(hand[nrHands-1].getSize() != 2) return null;
		
		//falta acabar
		ListIterator<Card> iterator = hand[nrHands-1].getHand().listIterator();
		Card aux = iterator.next();
		if(aux.equals(iterator.next())){
			iterator.remove();
			return aux;
		}
		Hand newHand = new Hand(h.split(), hit(s));

		return newHand;
	}
	
	/**
	 * 
	 * @param
	 * @return
	 * 
	 * @see 
	 */
	public void insure(){
		
		
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
