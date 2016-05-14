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
	private Scanner reader;
	
	/**
	 * Constructor for an interactive-mode Playing Area
	 * 
	 * @param minBet is the minimum bet accepted for the game
	 * @param maxBet is the maximum bet accepted for the game
	 * @param initialMoney is the initial balance of the player
	 * @param nbDecks is the number of decks in the shoe
	 * @param shufflePercent is the percentage of the shoe that must have been played so the shoe is shuffled
	 */
	public PlayingAreaInteractive(int minBet, int maxBet, float initialMoney, int nbDecks, int shufflePercent) {
		super(minBet, maxBet, initialMoney);
		
		handIndex = 0;//initial hand number to be used in hand array index
		
		try{
			this.shoe = new Shoe(nbDecks);
		}catch(IllegalDeckNumberException e){
			printMessage(e.getMessage());
			System.exit(1);
		}
		
		this.shufflePercentage = shufflePercent;	
		
		//Used to get input from player
		reader = new Scanner(System.in);
		reader.useDelimiter("[\r\n/]");
		
		ad = new Advisor(this.minBet,this.maxBet,nbDecks);
		
	}
	
	/**
	 * Reads the command from input line
	 */
	public String getCommand(){
		return reader.next();
	}
	
	/**
	 * Closes input reader and exits the game
	 */
	public void quit(){
		reader.close();
		System.out.println("GAME OVER");
		System.exit(0);
	}
	
	/**
	 * Prepares for next round of blackjack, according to the mode's needs<br>
	 * It resets both player's and dealer's hands, returning the cards to the shoe.<br>
	 * In interactive mode, it checks the shoe (if it needs shuffling, it shuffles) and resets the strategies.
	 */
	public void prepareNextRound(){
		super.prepareNextRound();
		//Shuffle checking is needed in this mode
		if(shoe.shuffle(shufflePercentage)){
			//reset strategies
			ad.resetStrats();
		}
	}

}
