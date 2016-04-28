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
	int minBet;
	int maxBet;
	int initBalance;
	
	public PlayingArea(int minBet, int maxBet, int initBalance) {
		this.minBet = minBet;
		this.maxBet = maxBet;
		this.initBalance = initBalance;
	}

}
