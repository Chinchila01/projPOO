package project;

public class Card {
	private static final char[] suits = new char[] {'C','D','S','H'};
	private static final char[] types = new char[] {'N','J','K','Q'};
	private final int suit;
	private final int symbol;
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
		if(types[symbol] == 'N') str = "[" + score + suits[suit] + "]";
		else str = "[" + types[symbol] + suits[suit] + "]";
		return str;
		//dfjaf
	}
}
