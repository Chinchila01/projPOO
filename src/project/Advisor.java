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
	
	public Advisor(int minBet, int maxBet, int nbDecks) {
		a5 = new AceFiveStrategy(minBet, maxBet);
		hls = new HiLoStrategy();
		useBasicStrat = (nbDecks >= 4); //use basic strategy if shoeSize >= 4
		useHiLo = true;
		useAF = true;
		stdStratBet = minBet;
		this.minBet = minBet;
		this.maxBet = maxBet;
		
	}
	
	public Advisor(int minBet, int maxBet, int nbDecks, String comb) {
		String[] s = comb.split("-");
		if(s.length == 1) useAF = false;
		else useAF = true;
		if(s[0].equals("BS")){
			useBasicStrat = true;
			useHiLo = false;
		}else {
			useBasicStrat = false;
			useHiLo = true;
		}
	}
	
	public void observeCard(Card c,float decksLeft){
		if(useAF) a5.observeCard(c);
		if(useHiLo) hls.updateCount(c, decksLeft);
	}
	
	public String[] advise(boolean dealDone,Player player, Card dealerCard){
			Hand h = player.getCurrHand();
			boolean canInsure = !h.hitDone && dealDone && !h.standDone;
			//String basicStrat = this.basicInterpret(dealDone,player,BasicStrategy.advise(h,dealerCard));
			char hlStratChar = hls.getStrat(h, dealerCard, canInsure);
			
			//if(useBasicStrat) System.out.println("basic\t" + basicStrat);
			//if(useHiLo && useBasicStrat) System.out.println("hi-lo\t" + ((hlStrat == '0') ? basicStrat : hlInterpret(hlStrat)));
			
			//String basicStrat = (useBasicStrat) ? this.basicInterpret(dealDone,player,BasicStrategy.advise(h,dealerCard)) : "";
			//String hlStrat = (useBasicStrat && useHiLo) ? ((hlStratChar == '0') ? basicStrat : hlInterpret(hlStratChar)) : "";
			
			String basicStrat = (useBasicStrat) ? BasicStrategy.advise(h,dealerCard) : "";
			String hlStrat = (useBasicStrat && useHiLo) ? ((hlStratChar == '0') ? basicStrat : String.valueOf(hlStratChar)) : "";
			
			
			String[] str = new String[2];
			str[0] = basicStrat;
			str[1] = hlStrat;
			return str;
	}
	
	public String advise(int lastBet){
		//if(useAF) System.out.println("ace-five\tbet " + a5.adviseBet(lastBet));
		//else System.out.println("Standard\tbet " + stdStratBet);
		
		if(useAF) return String.valueOf(a5.adviseBet(lastBet));
		else return String.valueOf(stdStratBet);
	}
	
	
	public String basicInterpret(boolean dealDone, Player player, String s){
		if(s.equals("H")) return "hit";
		if(s.equals("S")) return "stand";
		if(s.equals("P")) return "split";
		if(s.equals("Dh")){
			if(dealDone) return "hit";
			else return "double";
		}
		if(s.equals("Ds")){
			if(dealDone) return "stand";
			else return "double";
		}
		if(s.equals("Rh")){
			if(player.hand.size() > 1 || dealDone){
				return "hit";
			}
			else return "surrender";
		}
		else return s;
	}
	
	public String hlInterpret(char hlStrat){
		switch(hlStrat){
		case 's': return "stand";
		case 'h': return "hit";
		case 'd': return "double";
		case 'u': return "surrender";
		case 'p': return "split";
		case 'i': return "insurance";
		}
		
		return "0";
	}
	
	public void updateStdStrat(boolean playerLost){
		stdStratBet += (playerLost) ? -minBet : minBet;
		if(stdStratBet < minBet) stdStratBet = minBet;
		if(stdStratBet > maxBet) stdStratBet = maxBet;
	}
}
