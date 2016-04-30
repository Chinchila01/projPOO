package project;

public class Dealer implements PlayerInterface{
	
	Hand hand;
	
	/**
	 * Implementation of inherited method hit
	 * 
	 * @param Shoe
	 * @return Card object
	 * @see hit
	 */
	
	public Dealer (){
		hand = new Hand(null, null);
	}
	
	//nao precisa de receber a hand pois o dealer apenas tem uma hand a cada jogada
	public void hit(Hand h, Shoe s){
		// se a carta recebida for um ace e fizer bust, ace passa a valer 1
		Card c = s.getNext();
		if(c.getSymbol() == 4) c.setScore(1);
		hand.addCard(c);
	}
	
	/**
	 * Implementation of inherited method stand
	 * 
	 * @see stand
	 */
	public int stand(int curHand){
		return 0;
	}
	
	public String getHands() {
		StringBuilder sb = new StringBuilder();
		for(Card c : hand.getHand()) {
			if(c.isTurnedUp)
				sb.append(c.toString());
			else
				sb.append("[X]");
		}
		return sb.toString();
	}
	
	public void resetHands(Shoe s){
		for(Card c : hand.getHand()){
			s.addLast(c);
		}
		hand = new Hand(null,null);
	}
	
	public int getScore(Hand h) {
		int score=0;
		for(Card c : h.cards) {
			score += c.getScore();
		}
		return score;
	}

}
