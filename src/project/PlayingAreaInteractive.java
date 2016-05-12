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
	private Scanner reader;
	
	public PlayingAreaInteractive(int minBet, int maxBet, float initialMoney, int nbDecks, int shufflePercent) {
		super(minBet, maxBet, initialMoney);
		
		handIndex = 0;//initial hand number to be used in hand array index
		
		this.shoe = new Shoe(nbDecks);
		
		this.shufflePercentage = shufflePercent;	
		
		//Used to get input from player
		reader = new Scanner(System.in);
		reader.useDelimiter("[\r\n/]");
		
		ad = new Advisor(this.minBet,this.maxBet,nbDecks);
		
	}
	
	public String getCommand(){
		return reader.next();
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
	
/*	public static void main(String[] args) {
		
		PlayingAreaInteractive pa = new PlayingAreaInteractive(args);
		Player player = new Player(pa.initialMoney, pa.minBet, pa.maxBet);
		Dealer dealer = new Dealer(pa.initialMoney, pa.minBet, pa.maxBet);
		
		String cmd;
		
		while(true) {
			
			
			while(player.getNextHand() != null) {	// player's turn
				
				while(true) {
					System.out.println("Player's turn.");
					cmd = pa.getCommand();	//get player input
					try {
						pa.executePlayerAction(cmd, player, dealer);
						
						break;
					} catch (IllegalCmdException e) {
						System.out.println(e.getMessage());
						continue;
					}
				}
				
			}//end_player_turn
			
			//dealer turn
			pa.dealerTurn(dealer);
			
			//payout time
			pa.payOut(player, dealer);
			
			//reset hands
			pa.prepareNextRound(player,dealer);
			
			
		}//end_rounds
		
	}//end_main
*/
}
