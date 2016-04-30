package project;

import java.util.ListIterator;

public class Player extends SuperPlayer{

	
	/**
	 * Constructor for a Player object. Needs a object player to be created.
	 * 
	 * @param Object player
	 * @see Player Constructor
	 */
	public Player(float initialMoney, int minBet, int maxBet){
		super(initialMoney, minBet, maxBet);
	}
	
	/*
	public int nextHand(int curHand){
		if (curHand<nrHands-1){	//if exists return the next hand to be played
			System.out.println("playing "+ curHand + "nd hand...\n");
			return curHand ++;
		}
		
		return -1;
	}*/
	/**
	 * When you get two starting cards of the same face value, you have the option to split the hand 
	 * in two. You place another bet of the same size as the original bet and play on with two hands.
	 * 
	 * @param Hand h to be split 
	 * @param Shoe s, from which the new card will be picked through hit method
	 * 
	 * 
	 * @see 
	 */
	public void split(Hand h, Shoe s){
		
		if(h.getSize() != 2) { //Check if the hand has 2 cards TODO: if not Add exception
			
		}
			
		ListIterator<Card> iterator = h.getHand().listIterator();
		Card aux = iterator.next();
		if(!aux.equals(iterator.next())){ // TODO: throw exception if false
			
		} 
		iterator.remove();//remove last (repeated) card from hand
		hit(h,s);//get a card from shoe
		
		Hand newHand;
		newHand = new Hand(aux,null,minBet,maxBet);
		hit(newHand,s); //Immediately get a new card for newHand
		newHand.curBet=h.curBet; //set the same bet for newHand
		hand.add(newHand);//add hand to the hands list
	}
	
	/**
	 * When the dealer's face-up card is an ace, each player gets the chance to bet on whether the
	 * dealer has a blackjack. 
	 * @param Hand dh -  dealer's hand
	 * @return insurance bet value
	 * 
	 * @see 
	 */
	public int insurance(Hand dealerHand,Hand playerHand){
		
		//only check the card that is initially faced up
		if (dealerHand.getHand().iterator().next().getType()=='A')
			return playerHand.curBet;//insurance bet 
		else
			;//TODO:throw a exception
		return 0;
	
	}
	
	/**
	 * 
	 * @param
	 * @return
	 * 
	 * @see 
	 */
	public double surrender(Hand dealerHand, Hand playerHand){
		if(dealerHand.getSize() == 2 && dealerHand.getScore() == 21) {
			return 0;
		}
		else
			return playerHand.curBet/2;
		
	}
/*
	public boolean doubleBet(int handIndex){
		int handScore = hand[handIndex].getScore(); // score of current hand
		int nbCards = hand[handIndex].getSize(); // number of cards in current hand
		if(nrHands == 1 && nbCards == 2 && handScore > 8 && handScore < 12){
			if((getPlayerMoney() - hand[handIndex].curBet) < 0) return false;
			hand[handIndex].addBet(hand[handIndex].curBet);
			addPlayerMoney(-hand[handIndex].curBet);
			return true;
		}
		return false;
	}
	*/
	
	public double getPlayerMoney() {
		return playerMoney;
	}

	public boolean addPlayerMoney(float playerMoney) {
		float money = this.playerMoney + playerMoney;
		if(money > 0){
			this.playerMoney = money;
			return true;
		}
		return false;
	}

	
}
