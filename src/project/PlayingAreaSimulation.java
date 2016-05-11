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
	int strategy;
	
	int handIndex;
	int previousBet;
	public static int minimumBet;
	
	public PlayingAreaSimulation(String[] args) {
		//TODO:faltam restriçoes na passagem dos comandos - ver enunciado. 
		//Muito provavelmente falta tb nos outros modos de jogo
		super(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		if(args.length != 8) {
			System.out.println("Invalid number of arguments for simulation mode.");
			System.exit(-1);
		}
		this.nbDecksInShoe = Integer.parseInt(args[4]);
		this.shufflePercentage = Integer.parseInt(args[5]);
		this.nbShuffles = Integer.parseInt(args[5]);
		this.strategy = Integer.parseInt(args[6]);
		
		previousBet = minBet;
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

}
