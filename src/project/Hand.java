package project;

import java.util.ArrayList;
import java.util.Iterator;

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
	boolean hasBlackjack;	//TODO: isto devia ser um metodo nao? as vezes nao detecta blackjack
	boolean insuranceDone;
	boolean hitDone;
	boolean standDone;
	boolean doubleDone;
	boolean surrenderDone;
	
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
		this.minBet = minBet;
		this.maxBet = maxBet;
		if(first != null){
			cards.add(first);
		}
		if(second != null){
			cards.add(second);
		}
		curBet=0;
		hitDone=false;
		standDone=false;
		doubleDone=false;
		surrenderDone=false;
		insuranceDone=false;
	}
	
	
	/**
	 * Returns the cards that constitute this {@link Hand}
	 * @return the hand
	 * @see Hand
	 * @see Card
	 */
	public ArrayList<Card> getCards(){
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
	public int addBet(int b) throws IllegalCmdException{
		if(b < 0) throw new IllegalCmdException("bet value is less than zero");
		if(b > maxBet) throw new IllegalCmdException("bet value is too high");
		if(b < minBet) throw new IllegalCmdException("bet value is too low");
		curBet=curBet + b;
		return b;
	}
	
	
	/**
	 * Iterates over cards and returns sum of each card's score.
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

	/**
	 * Test if any side rule has been done
	 * @return
	 */
	public boolean sideRuleDone(){
		return insuranceDone || doubleDone || surrenderDone;
	}
	
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Card c : cards) {
			sb.append(c.toString());
		}
		return sb.toString();
	}
	
	/**
	 * Checks if the double side rule is available to be executed
	 * @return boolean
	 */
	public boolean doubleAvailable(){
		return (this.getScore()>8 && this.getScore()<12 && !sideRuleDone() && !hitDone && !standDone);
	}
	
	/**
	 * Check if the next card is equal to the actual card
	 * @return boolean
	 */
	public boolean cardsEqual(){
		Iterator<Card> it = cards.iterator();
		return it.next()==it.next();
	}
	
}
