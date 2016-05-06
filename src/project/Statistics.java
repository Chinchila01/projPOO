package project;

public class Statistics {
	private int playerBJ, dealerBJ;
	private int wins,losses,pushes;
	private int plays;
	private double balance;
	public final double initBalance;
	
	public Statistics(double initBalance){
		this.initBalance = initBalance;
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	/**
	 * Adds a win to the statistics
	 */
	public void addWin(){
		this.wins++;
		this.plays++;
	}
	
	/**
	 * Adds a loss to the statistics
	 */
	public void addLoss(){
		this.losses++;
		this.plays++;
	}
	
	/**
	 * Adds a push to the statistics
	 */
	public void addPush(){ 
		this.pushes++;
		this.plays++;
	}
	
	/**
	 * Adds a blackjack from the player to the statistics
	 */
	public void addPlayerBJ(){
		this.playerBJ++;
	}
	
	/**
	 * Adds a blackjack from the dealer to the statistics
	 */
	public void addDealerBJ(){
		this.dealerBJ++;
	}
	
	/** 
	 * Prints the statistics, with the following statistics: <br>
	 * <p>BJ P/D  -> playerBlackJacks/dealerBlackJacks<br>
	 *    Win -> playerWins <br>
	 *    Lose -> playerLosses <br>
	 *    Push -> playerPushes <br>
	 *    Balance -> absoluteBalance(relativeBalance%)</p>
	 * Relative balance is relative to the initial balance
	 * 
	 * @throws NoPlayedRoundsException
	 */
	public void presentStatistics() throws NoPlayedRoundsException{
		if(plays == 0) throw new NoPlayedRoundsException();
		System.out.printf("BJ P/D %6.2f/%.2f\n",(float)playerBJ/plays,(float)dealerBJ/plays);
		System.out.printf("Win %11.2f\n",(float)wins/plays);
		System.out.printf("Lose %10.2f\n",(float)losses/plays);
		System.out.printf("Push %10.2f\n",(float)pushes/plays);
		System.out.printf("Balance %5.2f(%.0f%%)\n",balance,balance/initBalance*100);
	}
}

