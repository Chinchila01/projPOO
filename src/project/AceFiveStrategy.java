package project;

/** AceFiveStrategy class
 * 
 * Advises the amount of the bet.
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class AceFiveStrategy {
	
	int minBet;
	int maxBet;
	static int count = 0;
	
	/**
	 * Constructor for a AceFiveStratagy object.
	 * 
	 * @param Minimum Bet
	 * @param Maximum Bet
	 * @see AceFiveStrategy
	 */
	public AceFiveStrategy(int minBet, int maxBet) {
		this.minBet = minBet;
		this.maxBet = maxBet;
	}
	
	/**
	 * Method to count the Aces and fives that are played 
	 * @param card
	 */
	public void observeCard(Card card) {
		if(card.getSymbol() == '5') count++;
		else if(card.getSymbol() == 'A') count--;
	}
	/**
	 * Bet Advisor for the Ace five strategy
	 * @param lastBet
	 * @return bet according to this strategy 
	 * @see AceFiveStrategy
	 */
	public int adviseBet(int lastBet) {
		if(count>=2) return lastBet*2;
		else return minBet;
	}
	/**
	 * Reset counter for the @see AceFiveStrategy
	 */
	public void reset(){
		count = 0;
	}

}
