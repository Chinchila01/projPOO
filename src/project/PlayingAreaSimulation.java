package project;

/**
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class PlayingAreaSimulation extends PlayingArea {
	
	int nbDecksInShoe;
	int shufflePercentage;
	int nbShuffles;
	String strategy;
	int handIndex;
	int previousBet;
	public static int minimumBet;
	
	public PlayingAreaSimulation(int minBet, int maxBet, float initialMoney, int nbDecks, int shufflePercent, int nbShuffles, String strat) {
		//TODO:faltam restriçoes na passagem dos comandos - ver enunciado. 
		//Muito provavelmente falta tb nos outros modos de jogo
		super(minBet, maxBet, initialMoney);

		this.nbDecksInShoe = nbDecks;
		this.shufflePercentage = shufflePercent;
		this.nbShuffles = nbShuffles;
		this.strategy = strat;
		previousBet = minBet;
		
		//Creating advisor
		this.ad = new Advisor(minBet, maxBet, nbDecks, strat);
	}
	
	//TODO: fix, temporary
	public String getCommand(){
		return "";
	}
	
	//TODO: Fix, temporary
	public boolean hasNextCommand(){
		return false;
	}
		
	//TODO: fix, temporary
	public void quit(){
		System.exit(0);
	}
	
	@Override
	public void printMessage(String s){
		//No messages are printed in simulation mode
	}

}
