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
	ArrayList<Card> hand;
	
	/**
	 * Constructor for a Hand object. Needs 2 cards to be created.
	 * 
	 * @param first card to add to the {@link Hand}
	 * @param second card to add to the {@link Hand}
	 * @see Card
	 */
	public Hand(Card first, Card second){
		hand = new ArrayList<Card>();
		if(first != null && second != null){
			hand.add(first);
			hand.add(second);
		}
		
	}
	
	/**
	 * Returns the cards that constitute this {@link Hand}
	 * @return the hand
	 * @see Hand
	 * @see Card
	 */
	public ArrayList<Card> getHand(){
		return hand;
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
