package project;

/** AceFiveStrategy class
 * 
 * Advises the amount of the bet.
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class AceFiveStrategy {
	
	int minBet;
	int maxBet;
	static int count = 0;
	
	public AceFiveStrategy(int minBet, int maxBet) {
		this.minBet = minBet;
		this.maxBet = maxBet;
	}
	
	public void observeCard(Card card) {
		if(card.getSymbol() == '5')
			count++;
		else if(card.getSymbol() == 'A') 
			count--;
	}
	
	//TODO: throw exception? acho que nao e preciso
	public int adviseBet(int lastBet) {
		if(count>=2)
			return lastBet*2;
		if(count<=1)
			return minBet;
		
		// Should never reach here
		return -1;
	}

}
