package project;

import java.util.ListIterator;

public class Player implements PlayerInterface{

	
	private int playerMoney;
	
	Hand[] hand;
	short nrHands;
	int minBet,maxBet;
	/**
	 * Constructor for a Player object. Needs a object player to be created.
	 * 
	 * @param Object player
	 * @see Player Constructor
	 */
	public Player(int initialMoney, int minBet){
		playerMoney=initialMoney;
		hand = new Hand[4];
		
		hand[0] = new Hand(null, null, minBet, maxBet);//depois o dealer é que vai dar as cartas
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
	public void hit(Hand h,Shoe s){
		h.addCard(s.getNext());
	}
	
	/**
	 * Implementation of inherited method stand
	 * This move is supposed to not get any further card and stick with the actual hand
	 * against the Dealer's Hand
	 * 
	 * @return index of the next hand to be played; If returned value=-1 : there are no more hands to be played
	 * @see stand
	 */
	public int stand(int curHand){
		if (curHand<nrHands){//if exists return the next hand to be played
			System.out.println("playing "+ curHand + "nd hand...\n");
			return curHand ++;
		}
		return -1;
		
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
			
		ListIterator<Card> iterator = h.getHand().listIterator();
		Card aux = iterator.next();
		if(aux.equals(iterator.next())){
			iterator.remove();
		}
		
		hit(h,s);//get a card from shoe
		Hand newHand = hand[nrHands-1+1];
		newHand = new Hand(aux, null, minBet, maxBet);//create a new hand 
		nrHands++; 
		hit(newHand,s);
		newHand.curBet=h.curBet;
		
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
			return ph.curBet;//insurance bet 
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

	public int getPlayerMoney() {
		return playerMoney;
	}

	public void addPlayerMoney(int playerMoney) {
		this.playerMoney = this.playerMoney + playerMoney;
	}
}
