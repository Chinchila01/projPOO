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
public class Advisor {	//TODO: so usar BasicStrategy se decks >=4
	
	String[] combination;
	int betStrategy;
	AceFiveStrategy a5;
	HiLoStrategy hls;
	
	public Advisor(int minBet, int maxBet) {
		a5 = new AceFiveStrategy(minBet, maxBet);
		hls = new HiLoStrategy();
	}
	
	public Advisor(String comb) {
		
	}
	
	public void observeCard(Card c,float decksLeft){
		a5.observeCard(c);
		hls.updateCount(c, decksLeft);
	}
	
	//TODO: BasicStrategy usar apenas se numero de decks >= 4
	public void advise(boolean dealDone,Player player, Card dealerCard){
			Hand h = player.getCurrHand();
			boolean canInsure = !h.hitDone && dealDone && !h.standDone;
			String basicStrat = this.basicInterpret(dealDone,player,BasicStrategy.advise(h,dealerCard));
			char hlStrat = hls.getStrat(h, dealerCard, canInsure);
			System.out.println("basic\t" + basicStrat);
			System.out.println("hi-lo\t" + ((hlStrat == '0') ? basicStrat : hlInterpret(hlStrat)));
	}
	
	public void advise(int lastBet){
		System.out.println("ace-five\tbet " + a5.adviseBet(lastBet));
	}
	
	
	private String basicInterpret(boolean dealDone, Player player, String s){
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
		else return s; //TODO: add exception???
	}
	
	private String hlInterpret(char hlStrat){
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
}
