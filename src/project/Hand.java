package project;

import java.util.ArrayList;

/** Hand class
 * A hand is composed of two or more cards.
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 */
public class Hand {
	/**
	 * ArrayList where the Hand is stored. Minimum size is 2, as that is the amount of cards first dealt by the dealer to each player
	 * @see Card
	 */
	ArrayList<Card> cards;
	int curBet,minBet,maxBet;
	boolean busted;
	boolean hasBlackjack;
	boolean insured;
	/**
	 * Constructor for a Hand object. Needs 2 cards to be created, a minimum bet value and a maximum bet value
	 * 
	 * @param first card to add to the {@link Hand}
	 * @param second card to add to the {@link Hand}
	 * @param minimum bet value
	 * @param maximum bet value
	 * @see Card
	 */
	public Hand(Card first, Card second, int minBet, int maxBet){
		cards = new ArrayList<Card>();
		if(first != null && second != null){
			cards.add(first);
			cards.add(second);
		}
		curBet=0;
	}
	
	
	/**
	 * Returns the cards that constitute this {@link Hand}
	 * @return the hand
	 * @see Hand
	 * @see Card
	 */
	public ArrayList<Card> getHand(){
		return this.cards;
	}
	
	/**
	 * Adds a card to the hand
	 * @param c is the card to be added
	 * @see Card
	 */
	public void addCard(Card c){
		if(c != null) cards.add(c);
	}
	
	/**
	 * get number of cards in this hand
	 * @return integer number of cards in this hand
	 * @see Card
	 */
	public int getSize(){
		return cards.size();
	}
	
	/**
	 * Adds the value passed to the current bet, if it's higher than maxBet, adds maxBet instead
	 * @return value that was bet
	 * @see addBetValue
	 */
	public int addBet(int b){
		curBet=curBet + b;
		return b;
	}
	
	
	/**
	 * Iterates over cards and returns sum of each card's score.
	 * 
	 * @return total score of hand
	 */
	public int getScore() {
		int points=0;
		for(Card c : cards) {
			points += c.getScore();
		}
		
		if (points==21 && this.getSize()==2) hasBlackjack=true;
		else if (points>21) busted=true;
		
		return points;
	}


	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Card c : cards) {
			sb.append(c.toString());
		}
		return sb.toString();
	}
	
	
	
}
