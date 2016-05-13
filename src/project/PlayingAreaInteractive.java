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
	
	public String getCommand(){
		return reader.next();
	}
	
	public void quit(){
		reader.close();
		System.out.println("GAME OVER");
		System.exit(0);
	}
	
	public void prepareNextRound(){
		super.prepareNextRound();
		//Shuffle checking is needed in this mode
		if(shoe.shuffle(shufflePercentage)){
			//reset strategies
			ad.resetStrats();
		}
	}

}
