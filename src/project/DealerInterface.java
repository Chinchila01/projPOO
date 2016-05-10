package project;

/** PlayerInterface interface
 * Interface to the basic movements a BlackJack player can execute
 * 
 * @author Filipe Correia
 * @author Hélder Duarte
 * @author João Vieira
 */
public interface DealerInterface {
	
	//TODO: make this a superclass
	
	/**
	 * This method is used to ask for one card that will be randomly picked from shoe
	 * 
	 * @param Shoe that contains all the decks
	 * @return card picked randomly
	 * @see Hit
	 */
	public Card hit(Shoe s);
	
	/**
	 * This method is used to keep the player's hand as it is.
	 * 
	 * @see Stand
	 */
	public void stand();
	
	public String getHand();
	
	public void resetHands(Shoe s);
}

