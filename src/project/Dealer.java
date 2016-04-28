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
		hand = new Hand(null,null);
	}
	
	//nao precisa de receber a hand pois o dealer apenas tem uma hand a cada jogada
	public void hit(Hand h, Shoe s){
		hand.addCard(s.getNext());
	}
	
	/**
	 * Implementation of inherited method stand
	 * 
	 * @see stand
	 */
	public int stand(int curHand){
	
		return 0;
	}

}
