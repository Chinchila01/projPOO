package project;

public class Player implements PlayerInterface{

	Player player1;
	
	/**
	 * Constructor for a Player object. Needs a object player to be created.
	 * 
	 * @param Object player
	 * @see Player Constructor
	 */
	public Player(Player p){
		player1=p;
	}
	
	/**
	 * Implementation of inherited method hit
	 * 
	 * @param Shoe
	 * @return Card object
	 * @see hit
	 */
	public Card hit(Shoe s){
		
		
		return null;
	}
	
	/**
	 * Implementation of inherited method stand
	 * 
	 * @see stand
	 */
	public void stand(){
		
		
	}
}
