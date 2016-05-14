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
	int nbShuffles,shufflesPlayed;
	String strategy;
	public static int minimumBet;
	
	/**
	 * Playing Area Simulator Constructor
	 * @param minBet
	 * @param maxBet
	 * @param initialMoney
	 * @param nbDecks
	 * @param shufflePercent
	 * @param nbShuffles
	 * @param strat
	 */
	public PlayingAreaSimulation(int minBet, int maxBet, float initialMoney, int nbDecks, int shufflePercent, int nbShuffles, String strat) {
		super(minBet, maxBet, initialMoney);
		this.nbDecksInShoe = nbDecks;
		this.shufflePercentage = shufflePercent;
		this.nbShuffles = nbShuffles;
		this.shufflesPlayed = 0;
		this.strategy = strat;
		
		this.ad = new Advisor(minBet, maxBet, nbDecks, strat);
		
		try{
			this.shoe = new Shoe(nbDecks);
		}catch(IllegalDeckNumberException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public String getCommand(){
		if(!dealDone && !betDone) return ad.betInterpret(ad.advise(previousBet));
		if(betDone && !dealDone)   return "d";
		else return ad.cmdInterpret(dealDone,player, ad.advise(dealDone, player,  dealer.hand.cards.iterator().next()));
	}
	
	
	public boolean hasNextCommand(){
		if(shufflesPlayed > nbShuffles) quit();
		return true;
	}
		
	public void quit(){
		try{
			stat.presentStatistics();
		} catch(NoPlayedRoundsException e){
			System.out.println("no rounds were played");
		}
		System.out.println("GAME OVER");
		System.out.println("number of rounds played: " + nbShuffles);
		System.exit(0);
	}
	
	/**
	 * Prepares for next round of blackjack, according to the mode's needs<br>
	 * It resets both player's and dealer's hands, returning the cards to the shoe.<br>
	 * In simulation mode, it also checks if the shuffling percentage was reached,<br>
	 */
	public void prepareNextRound(){
		super.prepareNextRound();
		//Shuffle checking is needed in this mode
		if(shoe.shuffle(shufflePercentage)) {
			shufflesPlayed++;
			//reset strategies
			ad.resetStrats();
		}
		betDone = false;
	}
	
	@Override
	public void handleMoneyException(String e){
		super.handleMoneyException(e);
		quit();
	}

}
