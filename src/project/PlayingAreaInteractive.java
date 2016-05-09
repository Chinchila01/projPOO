package project;

//import java.util.Iterator;
import java.util.Scanner;

/** 
 * This class controls all game logic.
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class PlayingAreaInteractive extends PlayingArea{
	
	int nbDecksInShoe;
	int shufflePercentage;
	int handIndex;
	public int previousBet;
	//public int minimumBet;
	private Scanner reader;
	
	public PlayingAreaInteractive(String[] args) { //TODO: em vez de receber args, devia receber as coisas discriminadas. o main trata dos args
		
		super(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Float.parseFloat(args[3]));
		System.out.println(this.minBet + " " + this.maxBet);
		handIndex = 0;//initial hand number to be used in hand array index
		
		this.shoe = new Shoe(Integer.parseInt(args[4]));
		
		if(args.length != 6) {
			System.out.println("Invalid number of arguments for interactive mode.");
			System.out.println("max-bet min-bet balance shoe shuffle");
			System.exit(-1);
		}
		
		//this.nbDecksInShoe = Integer.parseInt(args[4]);
		this.shufflePercentage = Integer.parseInt(args[5]);	
		
		//Used to get input from player
		reader = new Scanner(System.in);
	}
	
	public String getCommand(){
		return reader.next();
	}
	
	public boolean hasNextCommand(){
		return reader.hasNext();
	}
	
	public void quit(){
		reader.close();
		System.out.println("GAME OVER");
		System.exit(0);
	}
	
	public void prepareNextRound(Player player, Dealer dealer){
		super.prepareNextRound(player, dealer);
		//Shuffle checking is needed in this mode
		shoe.shuffle(shufflePercentage);
	}
	
	public static void main(String[] args) {
		
		PlayingAreaInteractive pa = new PlayingAreaInteractive(args);
		Player player = new Player(pa.initialMoney, pa.minBet, pa.maxBet);
		Dealer dealer = new Dealer(pa.initialMoney, pa.minBet, pa.maxBet);
		
		String cmd;
		
		while(true) {
			
			
			while(player.getNextHand() != null) {	// player's turn
				
				System.out.println("Player's turn.");
				cmd = pa.getCommand();	//get player input
				
				try {
					pa.executePlayerAction(cmd, player, dealer);
				} catch (IllegalCmdException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				} //parse input
			}//end_player_turn
			
			//dealer turn
			pa.dealerTurn(dealer);
			
			//payout time
			pa.payOut(player, dealer);
			
			//reset hands
			pa.prepareNextRound(player,dealer);
			
		}//end_rounds
		
	}//end_main

}
