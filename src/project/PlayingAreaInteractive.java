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
			
			while(pa.handIndex != -1) {
				
				
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
					if(player.hand[pa.handIndex].getScore() > 21) {
						System.out.println("player busts");
						pa.handIndex = player.nextHand(pa.handIndex);
						//break;
					}
					
					
				}
					
				if(cmd.equals("s")) {	//stand
					
					pa.handIndex = player.stand(pa.handIndex);
					//break;
				}
					
				if(cmd.equals("i")) {	// insurance
					
					player.insurance(null, null);
				}
					
				if(cmd.equals("u")) {	// surrender
					
					player.surrender();
				}
					
				if(cmd.equals("p")) {	// splitting
					
					player.split(player.hand[pa.handIndex], shoe);
				}
					
				if(cmd.equals("2")) {	// double
					int handScore = player.hand[pa.handIndex].getScore(); // score of current hand
					int nbCards = player.hand[pa.handIndex].getSize(); // number of cards in current hand
					if(player.nrHands == 1 && nbCards == 2 && handScore > 8 && handScore < 12){
						player.hand[pa.handIndex].addBet(player.hand[pa.handIndex].curBet);
					}
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
			dealer.hand.cards.iterator().next().isTurnedUp = true;
			System.out.println("dealer's hand " + dealer.getHands());
			while(dealer.hand.getScore() < 17) {
				dealer.hit(dealer.hand, shoe);
				System.out.println("dealer's hand " + dealer.getHands() + "(" + dealer.hand.getScore() + ")");
			}
			dealer.stand(0);
			System.out.println("dealer stands");
			
			for(int i = 0; i < player.nrHands; i++){
				//TODO: verificar blackjack antes (atencao ao numero de catas) -- done
				//TODO: verificar se o dealer tem blackjack -- done
				//TODO: atencao mais que uma mao, iterar -- done 
				//TODO: no hit verificar bust -- done
				//TODO: escolher valor do as
				
				// O jogador tem um blackjack
				if(player.hand[i].getScore() == 21 && player.hand[i].getSize() == 2){
					if(dealer.hand.getScore() == 21 && dealer.hand.getSize() == 2) {
						player.addPlayerMoney(bet);
						System.out.println("blackjack!!");
						System.out.println("player pushes and his current balance is " + player.getPlayerMoney());
					}
					else{
						player.addPlayerMoney(2.5*bet);
						System.out.println("player wins with a blackjack and his current balance is " + player.getPlayerMoney());
					}
				}
				
				if(dealer.hand.getScore() > 21) {	// Bust
					player.addPlayerMoney(2*bet);
					System.out.println("player wins and his current balance is " + player.getPlayerMoney());
				}
				else if(player.hand[i].getScore() > 21 || dealer.hand.getScore() > player.hand[i].getScore()) {
					System.out.println("player loses and his current balance is " + player.getPlayerMoney());
				}
				else {
					player.addPlayerMoney(2*bet);
					System.out.println("player wins and his current balance is " + player.getPlayerMoney());
				}
			}
			//TODO: Deitar fora as hands e nrHands
			player.resetHands();
			dealer.resetHands();
			pa.handIndex = 0;
			System.out.println("Starting a new round");
		}//end_rounds
		
	}//end_main

}
