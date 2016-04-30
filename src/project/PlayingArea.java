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
	float initialMoney;
	boolean validHands;
	
	public PlayingArea(int minBet, int maxBet, float initialMoney) {
		this.minBet = minBet;
		this.maxBet = maxBet;
		this.initialMoney = initialMoney;
	}

}
