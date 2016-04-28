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
		
		PlayingArea pa = new PlayingArea(args);
		Player player = new Player(pa.balance, pa.minBet);
		Dealer dealer = new Dealer();
		Scanner reader = new Scanner(System.in);
		Shoe shoe = new Shoe(pa.nbDecksInShoe);
		StringBuffer userArgs = new StringBuffer();
		Command cmd;
		
		
		switch(pa.gameMode) {
		
		case 'i'://
			//dar cartas
			player.hit(player.hand[0], shoe);
			player.hit(player.hand[0], shoe);
			//player's turn
			userArgs.replace(0, userArgs.length(), reader.nextLine());	//player input
			cmd = new Command(userArgs);
			switch(cmd.command) {
			case 'b':
				//se não receber argumentos no comando
				//System.out.println("player is betting " + player.hand[pa.handIndex].addBet());

				//se receber
				//System.out.println("player is betting " + player.hand[pa.handIndex].addBet(valor a ser apostado));
				
			case '$':
				System.out.println("Current balance: " + player.getPlayerMoney());
				break;
			case 'd':
				break;
			case 'h':
				player.hit(player.hand[pa.handIndex], shoe);
				System.out.println("player hits");
				System.out.println("player's hand" + player.hand);
				break;
			case 's':
				//player.stand();
				break;
			case 'i':
				player.insurance(null, null);
				break;
			case 'u':
				player.surrender();
				break;
			case 'p':
				player.split(null, shoe);
				break;
			case '2':
				break;
			//case "ad":	isto sao strings, ha que mudar o command para devolver string em vez de char para estes casos
			//case "st":
			}
			break;
			
		case 'd':
			break;
			
		case 's':
			break;
			
		}
		
		
		
	}

}
