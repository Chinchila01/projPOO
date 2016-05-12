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
	static final char[] suits = new char[] {'C','D','S','H'};
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
	static final char[] symbols = new char[] {'2','3','4','5','6','7','8','9','T','J','K','Q','A'};
	
	/**
	 * Used to convert char representations of cards to int representation @see Cards#types
	 */
	static final int[] scores = new int[] {2,3,4,5,6,7,8,9,10,10,10,10,11};
	
	/**
	 * Attribute that indicates the suit of this {@link Card}
	 * 
	 * @see Card
	 * @see Card#suits
	 */
	private final char suit;
	/**
	 * Attribute that indicates the symbol of this {@link Card}
	 * 
	 * @see Card
	 * @see Card#types
	 */
	private final char symbol;
	/**
	 * Attribute that indicates the value of a {@link Card} in blackjack
	 * 
	 * @see Card
	 */
	private int score;
	
	/**
	 * Attribute that indicates if the {@link Card} is turned up.
	 * Only useful if owner of {@link Card} is {@link Dealer}.
	 */
	boolean isTurnedUp;
	
	/**Card Constructor
	 * Constructor of a {@link Card} object
	 * 
	 * By default, the card is constructed turned up. 
	 * 
	 * @return new {@link Card} object
	 * @param suit of the card being created, according to {@link Card#suits}
	 * @param symbol of the card being created, according to {@link Card#types}
	 * @param score is the value of the card in blackjack
	 */
	public Card(char suit, char symbol, int score){
		this.suit = suit;
		this.symbol = symbol;
		this.score = score;
		this.isTurnedUp = true;
	}
	/**Card Constructor
	 * Constructor of a {@link Card} object
	 * 
	 * By default, the card is constructed turned up. 
	 * 
	 * @return new {@link Card} object
	 * @param card String depicting card in format [symbol|suit]
	 */
	public Card(String card) throws NotParseableException{
		this.score = getScoreFromSymbol(card);
		if(card.length()==3){
			this.symbol = 'T';
			this.suit = card.charAt(2);
		}
		else{
			this.symbol = card.charAt(0);
			this.suit = card.charAt(1);
		}
		this.isTurnedUp = true;
	}
	
	/**
	 * Auxiliary method to create Card from String. 
	 * @see Card
	 * 
	 * @param symbol char depicting score of card
	 * @return score according to symbol
	 */
	private int getScoreFromSymbol(String symbol) throws NotParseableException{
		char cSymbol=0;
		if(symbol.length() == 2)
			cSymbol = symbol.charAt(0);
		if(symbol.length() == 3)	// Special case for 10
			cSymbol = 'T';
		else throw new NotParseableException("symbol received is too long");
		return scores[(new String(symbols)).indexOf(cSymbol)];
	}

	/**
	 * Returns the suit of the {@link Card} object, according to {@link Card#suits}.
	 * @return suit of this card
	 */
	public char getSuit() {
		return suit;
	}
	/**
	 * Returns the symbol of the {@link Card} object, according to {@link Card#types}.
	 * @return suit of this card
	 */
	public char getSymbol() {
		return symbol;
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
		String s;
		String c;
		if(this.getSymbol()=='T')
			s = "10";
		else
			s = Character.toString(this.getSymbol());
		c = Character.toString(this.getSuit());
				
		return "[" + s + c + "]";
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
	
	/**
	 * Compares only score, not suit and symbol.
	 */
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
		return true;
	}
}
