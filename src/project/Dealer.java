package project;

public class Dealer implements DealerInterface{
	
	Hand hand;
	int minBet,maxBet;
	
	public Dealer (float initialMoney, int minBet, int maxBet){
		this.minBet=minBet;
		this.maxBet=maxBet;
		hand = new Hand(null, null, maxBet, maxBet);
	}
	
	/**
	 * adds a card from the shoe to the dealer's hand
	 */
	//nao precisa de receber a hand pois o dealer apenas tem uma hand a cada jogada
	public void hit(Shoe s){
		// se a carta recebida for um ace e fizer bust, ace passa a valer 1
		Card c = s.getNext();
		if(c.getSymbol() == 4) c.setScore(1);
		hand.addCard(c);
	}
	
	/**
	 * Gets the available hands from the dealer
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

	@Override
	public void resetHands(Shoe s) {
		s.addLast(hand.getCards());
		hand = new Hand(null, null,minBet,maxBet);
		
	}

	@Override
	public void stand() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString(){
		return "dealer's hand " + getHand();
	}
}
