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
		
		super(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Float.parseFloat((args[3])));
		
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
		
	public static void main(String[] args) {
		
		PlayingAreaInteractive pa = new PlayingAreaInteractive(args);
		Shoe shoe = new Shoe(pa.nbDecksInShoe);
		Player player = new Player(pa.initialMoney, pa.minBet, pa.maxBet);
		Dealer dealer = new Dealer(pa.initialMoney, pa.minBet, pa.maxBet);
		Statistics stat = new Statistics(pa.initialMoney);
		Scanner reader = new Scanner(System.in);
		String cmd;
		int bet=0;
		Hand playerCurrHand, dealerCurrHand;
		
		while(true) {
			playerCurrHand = player.hand.listIterator(0).next();
			// give cards to player
			player.hit(playerCurrHand, shoe);
			player.hit(playerCurrHand, shoe);
		
			// give cards to dealer
			dealerCurrHand = dealer.hand.listIterator(0).next();
			dealer.hit(dealerCurrHand, shoe);
			dealer.hit(dealerCurrHand, shoe);			
			dealerCurrHand.getHand().listIterator(1).next().isTurnedUp=false;//turn dealer's second card down
				
			while(player.hand.iterator().hasNext()) {
				
				// player's turn
				System.out.println("Player's turn.");
				cmd = reader.next();	//player input
				
						
				if(cmd.equals("b")) {
					
					if(reader.hasNext()) 	// betting without specifying amount, defaults to last bet
						bet = Integer.parseInt(reader.next());
					else bet = pa.previousBet;
					
					
					if(player.addPlayerMoney(-bet))
						System.out.println("player is betting " + playerCurrHand.addBet(bet));
					else System.out.println("player not able to bet, not enough money");
					
				}
					
				if(cmd.equals("$")) {	// prints current player balance
					System.out.println("Current balance: " + player.getPlayerMoney());
				}
					
				if(cmd.equals("d")) {
					System.out.println("dealer's hand " + dealer.getHands());
					System.out.println("player's hand " + player.getHands());
					
				}
					
				if(cmd.equals("h")) {	// hit
					player.hit(playerCurrHand, shoe);
					System.out.println("player hits");
					System.out.println("player's hand" + player.getHands());
					if(playerCurrHand.busted) {
						System.out.println("player busts");
						
						if(player.hand.iterator().hasNext()) 
							playerCurrHand=player.hand.iterator().next();//gets next hand if exists
					}
				}
					
				if(cmd.equals("s")) {	//stand
					if(player.hand.iterator().hasNext()) 
						playerCurrHand=player.hand.iterator().next();//gets next hand if exists
				}
					
				if(cmd.equals("i")) {	// insurance
					
					player.insurance(null, null);
				}
					
				if(cmd.equals("u")) {	// surrender
					
					//player.addPlayerMoney(player.surrender(dealer.hand, playerCurrHand));
				}
					
				if(cmd.equals("p")) {	// splitting
					
					player.split(playerCurrHand, shoe);
				}
					
				if(cmd.equals("2")) {	// double
					/*if(!player.doubleBet(pa.handIndex)){
						System.out.println("Doubling bet not possible, not enough money");
					}
					else System.out.println("");*/
				}
					
				if(cmd.equals("ad")) {	// advice
					
				}
					
				if(cmd.equals("st")) {	// statistics
					stat.presentStatistics();
				}
				
				if(cmd.equals("q")) {	// player inputs 'q' to quit the game
					reader.close();
					System.out.println("GAME OVER");
					System.exit(0);
				}			
					
				
			}//end_player_turn
			
			
			
			
			// dealer's turn
			//dealerCurrHand.getHand().listIterator(0).next().isTurnedUp = true;
			System.out.println("dealer's hand " + dealer.getHands() + " (" + dealerCurrHand.getScore() + ")");

			// turn hole
			dealerCurrHand.getHand().listIterator(1).next().isTurnedUp=true;
			
			while(dealerCurrHand.getScore() < 17) {
				dealer.hit(dealerCurrHand, shoe);
				System.out.println("dealer's hand " + dealer.getHands() + " (" + dealerCurrHand.getScore() + ")");
			}
			
			if(dealerCurrHand.hasBlackjack) {
				System.out.println("blackjack!!");
			}
			
			System.out.println("dealer stands");
			
			for(Hand eachHand : player.hand){
				//TODO: escolher valor do as
				//TODO: pushes
				
				// O jogador tem um blackjack
				if(eachHand.getScore() == 21 && eachHand.getSize() == 2){
					stat.addPlayerBJ();
					if(dealerCurrHand.hasBlackjack && dealerCurrHand.getSize() == 2) { // dealer tambem tem blackjack
						player.addPlayerMoney(eachHand.curBet);
						System.out.println("blackjack!!");
						System.out.println("player pushes and his current balance is " + player.getPlayerMoney());
						// Update statistics
						stat.addPush();
						stat.addDealerBJ();
					}
					else{ // dealer nao tem blackjack
						player.addPlayerMoney((float)2.5*eachHand.curBet);
						System.out.println("player wins with a blackjack and his current balance is " + player.getPlayerMoney());
						stat.addWin();
					}
				}
				
				else if(dealerCurrHand.busted) {	// dealer Bust
					player.addPlayerMoney(2*eachHand.curBet);
					System.out.println("player wins and his current balance is " + player.getPlayerMoney());
					stat.addWin();
				}
				else if(eachHand.busted || dealerCurrHand.getScore() > eachHand.getScore()) { // player bust ou dealer tem mais pontos
					System.out.println("player loses and his current balance is " + player.getPlayerMoney());
					stat.addLoss();
				}
				else if(eachHand.getScore() == dealerCurrHand.getScore()){
					player.addPlayerMoney(eachHand.curBet);
					System.out.println("player pushes and his current balance is " + player.getPlayerMoney());
					stat.addPush();
				}
				else { //player tem mais pontos
					player.addPlayerMoney(2*eachHand.curBet);
					System.out.println("player wins and his current balance is " + player.getPlayerMoney());
					stat.addWin();
				}
			}
			//TODO: Shuffling se a percentagem de shuffle for > que o threshold
			player.resetHands(shoe);
			dealer.resetHands(shoe);
			
			System.out.println("Starting a new round");
		}//end_rounds
		
	}//end_main

}
