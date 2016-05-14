package project;

/**
 * Dealer Class
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *@see DealerInterface
 */
public class Dealer implements DealerInterface{
	
	Hand hand;
	int minBet,maxBet;
	
	public Dealer (float initialMoney, int minBet, int maxBet){
		this.minBet=minBet;
		this.maxBet=maxBet;
		hand = new Hand(null, null, maxBet, maxBet);
	}
	

	
	/**
	 * Adds a card from the shoe to the dealer's hand
	 */
	//nao precisa de receber a hand pois o dealer apenas tem uma hand a cada jogada
	public Card hit(Shoe s){
		// se a carta recebida for um ace e fizer bust, ace passa a valer 1
		Card c = s.getNext();
		if(c.getSymbol() == 'A' && hand.getScore() > 10) c.setScore(1);
		hand.addCard(c);
		return c;
	}
	
	/**
	 * Gets the available hand from the dealer with second card turned down
	 * @return String of dealer's cards
	 */
	public String getHand() {
		StringBuilder sb = new StringBuilder();
		for(Card c : hand.getCards()) {
			if(c.isTurnedUp)
				sb.append(c.toString());
			else
				sb.append("[X]");
		}
		return sb.toString();
	}
	
	/**
	 * Reset Dealer's cards
	 * @param Shoe
	 */
	@Override
	public void resetHands(Shoe s) {
		s.addLast(hand.getCards());
		hand = new Hand(null, null,minBet,maxBet);
		
	}
	
	/**
	 * Empty method stand 
	 */
	@Override
	public void stand() {		
	}
	
	/**
	 * Print Dealer's hand
	 * @return String of cards
	 */
	@Override
	public String toString(){
		return "dealer's hand " + getHand();
	}
}
