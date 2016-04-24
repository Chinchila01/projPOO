package project;

/** 
 * This class contains all methods that operate/are related to the Card object.
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
	
	/**Card Constructor
	 * Constructor of a {@link Card} object
	 * 
	 * @return new {@link Card} object
	 * @param suit of the card being created, according to {@link Card#suits}
	 * @param symbol of the card being created, according to {@link Card#types}
	 * @param score is the value of the card in blackjack
	 */
	public Card(int suit,int symbol,int score){
		this.suit = suit;
		this.symbol = symbol;
		this.score = score;
	}

	/**
	 * Returns the suit of the {@link Card} object, according to {@link Card#suits}.
	 * @return suit of this card
	 */
	public int getSuit() {
		return suit;
	}
	/**
	 * Returns the symbol of the {@link Card} object, according to {@link Card#types}.
	 * @return suit of this card
	 */
	public int getSymbol() {
		return symbol;
	}
	
	/**
	 * Returns the type of the {@link Card} object, according to {@link Card#types}.
	 * @return character type of this card
	 */
	public char getType() {
		return types[this.getSymbol()];
	}
	
	/**
	 * Returns the score of the {@link Card} object in blackjack.
	 * @return score of the {@link Card}
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Because the Ace card can have two different values (1 or 11),<br> this method is needed to set the score according to what the<br> player chose.
	 * @params newScore new score of the {@link Card} object 
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + score;
		result = prime * result + suit;
		result = prime * result + symbol;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (score != other.score)
			return false;
		if (suit != other.suit)
			return false;
		if (symbol != other.symbol)
			return false;
		return true;
	}
}
