package project;

public class Dealer extends SuperPlayer{
	
	public Dealer (float initialMoney, int minBet, int maxBet){
		super(initialMoney, minBet, maxBet);
		
	}
	
	/**
	 * adds a card from the shoe to the dealer's hand
	 */
	//nao precisa de receber a hand pois o dealer apenas tem uma hand a cada jogada
	public void hit(Shoe s){
		// se a carta recebida for um ace e fizer bust, ace passa a valer 1
		Card c = s.getNext();
		if(c.getSymbol() == 4) c.setScore(1);
		hand.iterator().next().addCard(c);
	}
	
	/**
	 * Gets the available hands from the dealer
	 */
	public String getHands() {
		StringBuilder sb = new StringBuilder();
		for(Card c : hand.iterator().next().getCards()) {
			if(c.isTurnedUp)
				sb.append(c.toString());
			else
				sb.append("[X]");
		}
		return sb.toString();
	}
}
