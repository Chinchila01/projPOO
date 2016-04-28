package project;

/**
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public abstract class PlayingArea {
	
	/**
	 * Common attributes to all game modes
	 */
	int maxBet;
	int minBet;
	int balance;
	
	public PlayingArea(int maxBet, int minBet, int balance) {
		this.maxBet = maxBet;
		this.minBet = minBet;
		this.balance = balance;
	}

}
