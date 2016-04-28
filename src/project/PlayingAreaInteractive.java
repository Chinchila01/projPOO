package project;

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
	int previousBet;
	public static int minimumBet;
	
	public PlayingAreaInteractive(String[] args) {
		
		super(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		
		handIndex = 0;//initial hand number to be used in hand array index
		
		if(args.length != 6) {
			System.out.println("Invalid number of arguments for interactive mode.");
			System.out.println("max-bet min-bet balance shoe shuffle");
			System.exit(-1);
		}
		this.nbDecksInShoe = Integer.parseInt(args[4]);
		this.shufflePercentage = Integer.parseInt(args[5]);		
		
		previousBet = minBet;
	}
		
	
	//TODO: isto vai tudo para o game (?)
	public static void main(String[] args) {
		
		PlayingAreaInteractive pa = new PlayingAreaInteractive(args);
		Shoe shoe = new Shoe(pa.nbDecksInShoe);
		Player player = new Player(pa.initBalance);
		Dealer dealer = new Dealer();
		Scanner reader = new Scanner(System.in);
		String cmd;
		int bet=0;
		
		while(true) {
		
			// give cards to player
			player.hit(player.hand[0], shoe);
			player.hit(player.hand[0], shoe);
			
			// give cards to dealer
			dealer.hit(dealer.hand, shoe);
			dealer.hit(dealer.hand, shoe);
			dealer.hand.getHand().iterator().next().turnCard();
			
			while(true) {
				
				
				// player's turn
				System.out.println("Player's turn.");
				cmd = reader.next();	//player input
				
						
				if(cmd.equals("b")) {
					if(reader.hasNext()) {	// betting without specifying amount, defaults to last bet
						bet = Integer.parseInt(reader.next());
						if (bet>pa.maxBet) bet=pa.maxBet;
						if (bet<pa.minBet) bet=pa.minBet;
					}
					else {
						bet = pa.previousBet;
					}
					System.out.println("player is betting " + player.hand[pa.handIndex].addBet(bet));
					player.addPlayerMoney(-bet);
				}
					
				if(cmd.equals("$")) {	// prints current player balance
					System.out.println("Current balance: " + player.getPlayerMoney());
				}
					
				if(cmd.equals("d")) {
					System.out.println("dealer's hand " + dealer.getHands());
					System.out.println("player's hand " + player.getHands());
				
				}
					
				if(cmd.equals("h")) {	// hit
	
					player.hit(player.hand[pa.handIndex], shoe);
					System.out.println("player hits");
					System.out.println("player's hand" + player.getHands());
					
					
				}
					
				if(cmd.equals("s")) {	//stand
					
					player.stand(pa.handIndex);
					break;
				}
					
				if(cmd.equals("i")) {	// insurance
					
					player.insurance(null, null);
				}
					
				if(cmd.equals("u")) {	// surrender
					
					player.surrender();
				}
					
				if(cmd.equals("p")) {	// splitting
					
					player.split(null, shoe);
				}
					
				if(cmd.equals("2")) {	// double
					
				}
					
				if(cmd.equals("ad")) {	// advice
					
				}
					
				if(cmd.equals("st")) {	// statistics
					
				}
				
				if(cmd.equals("q")) {	// player inputs 'q' to quit the game
					reader.close();
					System.out.println("GAME OVER");
					System.exit(0);
				}			
					
				
			}//end_player_turn
			
			// dealer's turn
			
			while(dealer.hand.getScore() < 17) {
				dealer.hit(dealer.hand, shoe);
			}
			dealer.stand(0);
			
			//TODO: verificar blackjack antes (atencao ao numero de catas)
			//TODO: verificar se o dealer tem blackjack
			//TODO: atencao mais que uma mao, iterar
			//TODO: no hit verificar bust
			//TODO: escolher valor do ás
			
			if(dealer.hand.getScore() > 21) {	// Bust
				player.addPlayerMoney(2*bet);
				System.out.println("player wins and his current balance is " + player.getPlayerMoney());
			}
			
			else if(dealer.hand.getScore() > player.hand[0].getScore()) {
				System.out.println("player loses and his current balance is " + player.getPlayerMoney());
			}
			else {
				player.addPlayerMoney(2*bet);
				System.out.println("player wins and his current balance is " + player.getPlayerMoney());
			}
		
		}//end_rounds
		
	}//end_main

}
