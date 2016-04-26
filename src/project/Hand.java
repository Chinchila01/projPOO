package project;

import java.util.ArrayList;
import java.util.ListIterator;

/** Hand class
 * A hand is composed of two or more cards.
 * 
 * @author Filipe Correia
 * @author Hélder Duarte
 * @author João Vieira
 */
public class Hand {
	/**
	 * ArrayList where the Hand is stored. Minimum size is 2, as that is the amount of cards first dealt by the dealer to each player
	 * @see Card
	 */
	private ArrayList<Card> hand;
	int curBet, minBet, maxBet;
	
	/**
	 * Constructor for a Hand object. Needs 2 cards to be created.
	 * 
	 * @param first card to add to the {@link Hand}
	 * @param second card to add to the {@link Hand}
	 * @param minimum bet value
	 * @see Card
	 */
	public Hand(Card first, Card second, int minBet, int maxBet){
		hand = new ArrayList<Card>();
		if(first != null && second != null){
			hand.add(first);
			hand.add(second);
		}
		this.minBet=minBet;
		this.maxBet=maxBet;
		curBet=0;
	}
	
	/**
	 * Returns the cards that constitute this {@link Hand}
	 * @return the hand
	 * @see Hand
	 * @see Card
	 */
	public ArrayList<Card> getHand(){
		return this.hand;
	}
	
	/**
	 * Adds a card to the hand
	 * @param c is the card to be added
	 * @see Card
	 */
	public void addCard(Card c){
		if(c != null) hand.add(c);
	}
	
	/**
	 * get number of cards in this hand
	 * @return integer number of cards in this hand
	 * @see Card
	 */
	public int getSize(){
		return hand.size();
	}
	
	/**
	 * Adds the value passed to the current bet, if it's higher than maxBet, adds maxBet instead
	 * @return value that was bet
	 * @see addBetValue
	 */
	public int addBet(int b){
		if (b>maxBet) b=maxBet;
		curBet=curBet + b;
		
		return b;
	}
	
	/**
	 * If no value is passed, the default minBet is added to the current bet(curBet)
	 * @return value that was bet
	 * @see addBet
	 */
	public int addBet(){
		curBet=curBet + minBet;
		return minBet;
	}
	

	
	@Override
	public String toString(){
		String str;
		ListIterator<Card> it = hand.listIterator();
		StringBuilder sb = new StringBuilder();
		
		while(it.hasNext()){
			sb.append(it.next());
		}
		str = sb.toString();
		return str;
	}
	
}
