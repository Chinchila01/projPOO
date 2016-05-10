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
		AceFiveStrategy a5 = new AceFiveStrategy(minBet, maxBet);
		HiLoStrategy hls = new HiLoStrategy();
	}
	
	
	
	public Advisor(String comb) {
		
	}
	
	

}
