package project;

public class Statistics {
	private int playerBJ, dealerBJ;
	private int wins,losses,pushes;
	private int plays;
	private double balance;
	public final double initBalance;
	
	public Statistics(double initBalance){
		this.initBalance = initBalance;
		this.plays = 1; //avoid divide by zero
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	public void addWin(){
		this.wins++;
		if(this.plays > 1) this.plays++;
	}
	
	public void addLoss(){
		this.losses++;
		if(this.plays > 1) this.plays++;
	}
	
	public void addPush(){ 
		this.pushes++;
		if(this.plays > 1) this.plays++;
	}
	
	public void addPlayerBJ(){
		this.playerBJ++;
	}
	
	public void addDealerBJ(){
		this.dealerBJ++;
	}
	
	public void presentStatistics(){
		System.out.printf("BJ P/D %6.2f/%.2f\n",(float)playerBJ/plays,(float)dealerBJ/plays);
		System.out.printf("Win %11.2f\n",(float)wins/plays);
		System.out.printf("Lose %10.2f\n",(float)losses/plays);
		System.out.printf("Push %10.2f\n",(float)pushes/plays);
		System.out.printf("Balance %5.2f(%.0f%%)\n",balance,balance/initBalance*100);
	}
}

