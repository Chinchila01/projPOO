/**
 * 
 */
package project;

/** Advisor class
 * 
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class Advisor {
	
	String[] combination;
	int betStrategy;
	AceFiveStrategy a5;
	HiLoStrategy hls;
	boolean useBasicStrat;
	boolean useHiLo;
	boolean useAF;
	private int stdStratBet; // standard strategy bet value
	private int minBet;
	private int maxBet;
	
	/**
	 * Constructor for the advisor used in Debug and Interactive Mode
	 * @param minBet
	 * @param maxBet
	 * @param nbDecks
	 */
	public Advisor(int minBet, int maxBet, int nbDecks) {
		a5 = new AceFiveStrategy(minBet, maxBet);
		hls = new HiLoStrategy();
		useBasicStrat = (nbDecks >= 4); //use basic strategy if shoeSize >= 4
		useHiLo = useBasicStrat;
		useAF = true;
		this.stdStratBet = minBet;
		this.minBet = minBet;
		this.maxBet = maxBet;
		
	}
	/**
	 * Constructor for the advisor used in Simulation Mode
	 * @param minBet
	 * @param maxBet
	 * @param nbDecks
	 * @param comb
	 */
	public Advisor(int minBet, int maxBet, int nbDecks, String comb) {
		String[] s = comb.split("-");
		if(s.length == 1) useAF = false;
		else {
			useAF = true;
			a5 = new AceFiveStrategy(minBet, maxBet);
		}
		if(s[0].equals("BS")){
			useBasicStrat = true;
			useHiLo = false;
		}else {
			useBasicStrat = false;
			useHiLo = true;
			hls = new HiLoStrategy();
		}
		this.stdStratBet = minBet;
		this.minBet = minBet;
		this.maxBet = maxBet;
	}
	
	/**
	 * Counts cards dealt according to strategy used
	 * @param c - observed card
	 * @param decksLeft
	 */
	public void observeCard(Card c,float decksLeft){
		if(useAF) a5.observeCard(c);
		if(useHiLo) hls.updateCount(c, decksLeft);
	}
	
	/**
	 * Method called to advice based on player's and dealer's hand
	 * @param dealDone
	 * @param player
	 * @param dealerCard
	 * @return command advised by the basic and hi-lo strategy
	 */
	public String[] advise(boolean dealDone,Player player, Card dealerCard){
			Hand h = player.getCurrHand();
			boolean canInsure = !h.hitDone && dealDone && !h.standDone;
			char hlStratChar = '0';
			if(useHiLo) hlStratChar = hls.getStrat(h, dealerCard, canInsure);
			
			String basicStrat = (useBasicStrat) ? BasicStrategy.advise(h,dealerCard) : "";
			String hlStrat = (useHiLo) ? ((hlStratChar == '0') ? BasicStrategy.advise(h,dealerCard) : String.valueOf(hlStratChar)) : "";
			
			String[] str = new String[2];
			str[0] = basicStrat;
			str[1] = hlStrat;
			return str;
	}
	
	/**
	 * Advised the bet value
	 * @param lastBet
	 * @return String bet value
	 */
	public String advise(int lastBet){
		if(useAF) return String.valueOf(a5.adviseBet(lastBet));
		else return String.valueOf(stdStratBet);
	}
	
	/**
	 * Interprets the commands received from advisor into executable commands interpreted by the class PlayingArea
	 * @param dealDone
	 * @param player
	 * @param s
	 * @return commands to be executed
	 * @see PlayingArea
	 */
	public String cmdInterpret(boolean dealDone, Player player, String[] s){
		String str = (useBasicStrat) ? this.basicInterpret(dealDone, player, s[0]) : this.basicInterpret(dealDone,player,s[1]);
		
		if(str.equals("hit")) return "h";
		else if(str.equals("stand")) return "s";
		else if(str.equals("split")&& player.splitAvailable()) return "p"; 
		else if(str.equals("double") && player.getCurrHand().doubleAvailable()) return "2";
		else if(str.equals("surrender") && !player.getCurrHand().sideRuleDone()) return "u";
		else if(str.equals("insurance") && !player.getCurrHand().sideRuleDone()) return "i";
		else return player.getCurrHand().getScore()>=17 ?  "s" : "h";
	}
	
	/**
	 * Receives the bet value and return an executable command that bets the value received
	 * @param s - value to bet
	 * @return executable command bet
	 */
	public String betInterpret(String s){
		return "b " + s;
	}
	
	/**
	 * Interpreter of commands received from the advisor, using Basic and Hi-lo strategy 
	 * @param dealDone
	 * @param player
	 * @param s - commands to be interpreted
	 * @return executable command
	 */
	public String basicInterpret(boolean dealDone, Player player, String s){
		if(s.equals("H") || s.equals("h")) return "hit";
		if(s.equals("S")|| s.equals("s")) return "stand";
		if(s.equals("P")|| s.equals("p")) return "split";
		if(s.equals("Dh")){
			if(dealDone && !player.getCurrHand().hitDone && !player.getCurrHand().standDone ) return "double";
			else return "hit";
		}
		if(s.equals("Ds") || s.equals("d")){
			if(dealDone && !player.getCurrHand().hitDone && !player.getCurrHand().standDone ) return "double";
			else return "stand";
		}
		if(s.equals("Rh") || s.equals("u")){
			if(!player.getCurrHand().surrenderDone && player.hand.size() == 1 && dealDone && !player.getCurrHand().hitDone && !player.getCurrHand().standDone){
				return "surrender";
			}
			else return "hit";
		}
		else return s;
	}
	
	/**
	 * Update Standard strategy - If the player wins, increase the bet by
	 * min-bet (up to max-bet). If the player loses, decrease the bet by min-bet (down to min-bet).
	 * If the player pushes, keep the bet
	 * @param playerLost
	 */
	public void updateStdStrat(boolean playerLost){
		stdStratBet += (playerLost) ? -minBet : minBet;
		if(stdStratBet < minBet) stdStratBet = minBet;
		if(stdStratBet > maxBet) stdStratBet = maxBet;
	}
	
	/**
	 * Reset strategies
	 */
	public void resetStrats(){
		resetStdStrat();
		if(useAF) a5.reset();
		if(useHiLo) hls.reset();
	}
	
	/**
	 * Reset Standard strategy
	 */
	public void resetStdStrat(){
		stdStratBet = minBet;
	}
}
