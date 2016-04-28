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
		Player player = new Player(pa.balance, pa.minBet);
		Dealer dealer = new Dealer();
		Scanner reader = new Scanner(System.in);
		Shoe shoe = new Shoe(pa.nbDecksInShoe);
		String userArgs = new String();
		Command cmd;
		
		while(true) {
			
			// give cards to player
			player.hit(player.hand[0], shoe);
			player.hit(player.hand[0], shoe);
			
			// give cards to dealer
			dealer.hit(dealer.hand, shoe);
			dealer.hit(dealer.hand, shoe);
			dealer.hand.getHand().iterator().next().turnCard();
			
			// player's turn
			userArgs = reader.nextLine();	//player input
			cmd = new Command(userArgs);
					
			if(cmd.command.equals("b0")) {	// betting without specifying amount, defaults to last bet
				
				System.out.println("player is betting " + player.hand[pa.handIndex].addBet(pa.previousBet));
			}
				
			if(cmd.command.equals("b1")) {	// betting with amount specified
	
				System.out.println("player is betting " + player.hand[pa.handIndex].addBet(cmd.arg));
			}
				
			if(cmd.command.equals("$")) {	// prints current player balance
				
				System.out.println("Current balance: " + player.getPlayerMoney());
			}
				
			if(cmd.command.equals("d")) {
			
			}
				
			if(cmd.command.equals("h")) {
				
				player.hit(player.hand[pa.handIndex], shoe);
				System.out.println("player hits");
				System.out.println("player's hand" + player.hand);
			}
				
			if(cmd.command.equals("s")) {
				
				//player.stand();
			}
				
			if(cmd.command.equals("i")) {
				
				player.insurance(null, null);
			}
				
			if(cmd.command.equals("u")) {
				
				player.surrender();
			}
				
			if(cmd.command.equals("p")) {
				
				player.split(null, shoe);
			}
				
			if(cmd.command.equals("2")) {
				
			}
				
			if(cmd.command.equals("ad")) {	
				
			}
				
			if(cmd.command.equals("st")) {
				
			}
			
			if(cmd.command.equals("q")) {	// player inputs 'q' to quit the game
				System.exit(0);
			}
			
			// dealer's turn
			
			
			
		}
		
		
		
	}

}
