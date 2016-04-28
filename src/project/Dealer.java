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
	public void hit(Hand h, Shoe s){
		h.addCard(s.getNext());
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
