package project;

/** 
 * This class contains all methods that operate/are related to the Card object
 * 
 * @author Filipe Correia
 * @author Hélder Duarte
 * @author João Vieira
 *
 */
public class Card {
	/**
	 * Available suits that a {@link Card} can be: Clubs, Diamonds, Spades or Hearts
	 * 
	 * @see Card
	 * @see Deck
	 */
	private static final char[] suits = new char[] {'C','D','S','H'};
	/**
	 * Available types that a {@link Card} can be: <br>
	 * N -> Numeric <br>
	 * J -> Jack<br>
	 * K -> King <br>
	 * Q -> Queen <br>
	 * A -> Ace
	 * 
	 * @see Card
	 * @see Deck
	 */
	private static final char[] types = new char[] {'N','J','K','Q','A'};
	
	/**
	 * Attribute that indicates the suit of this {@link Card}
	 * 
	 * @see Card
	 * @see Card#suits
	 */
	private final int suit;
	/**
	 * Attribute that indicates the symbol of this {@link Card}
	 * 
	 * @see Card
	 * @see Card#types
	 */
	private final int symbol;
	/**
	 * Attribute that indicates the value of a {@link Card} in blackjack
	 * 
	 * @see Card
	 */
	private int score;
	
	public Card(int suit,int symbol,int score){
		this.suit = suit;
		this.symbol = symbol;
		this.score = score;
	}

	public int getSuit() {
		return suit;
	}

	public int getSymbol() {
		return symbol;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int newScore){
		//The Ace has multiple possible values
		this.score = newScore;
	}
	
	@Override
	public String toString(){
		String str;
		if(types[this.getSymbol()] == 'N') str = "[" + score + suits[this.getSuit()] + "]";
		else str = "[" + types[this.getSymbol()] + suits[this.getSuit()] + "]";
		return str;
	}
}
