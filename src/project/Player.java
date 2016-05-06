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
	public void split(Hand h, Shoe s) throws IllegalHandException{
		int tempIndex = currHand;
		if(h.getSize() != 2) throw new IllegalHandException("hand is too big");
		else{		
			ListIterator<Card> iterator = h.getCards().listIterator();
			Card aux = iterator.next();
			if(!aux.equals(iterator.next())) throw new IllegalHandException("cards are not equal"); // TODO: throw exception if false
			iterator.remove();//remove last (repeated) card from hand
			hit(s);//get a card from shoe
			
			Hand newHand;
			newHand = new Hand(aux,null,minBet,maxBet);
			hand.add(newHand);
			//temporarily change currHand to new hand
			currHand = hand.size()-1;
			hit(s); //Immediately get a new card for newHand
			currHand = tempIndex; //putting it back
			newHand.curBet=h.curBet; //set the same bet for newHand TODO: need to remove money from player
			hand.add(newHand);//add hand to the hands list
		}
	}
	
	/**
	 * When the dealer's face-up card is an ace, each player gets the chance to bet on whether the
	 * dealer has a blackjack. 
	 * @param Hand dh -  dealer's hand
	 * @return insurance bet value
	 * 
	 * @see 
	 */
	public void insurance(Hand dealerHand) throws IllegalHandException{
			//only check the card that is initially faced up
			if (dealerHand.getCards().iterator().next().getType()!='A') throw new IllegalHandException("dealer does not have an Ace");
			Hand playerHand = hand.listIterator(currHand).next();
			if(playerHand.getSize() != 2) throw new IllegalHandException("not at the beginning of the hand");
			if(playerHand.surrender) throw new IllegalHandException("player has already surrendered");
			if(playerHand.stand) throw new IllegalHandException("player has standed, insurance not available");
			playerHand.insured = true;//insurance bet 
	}
	
	/**
	 * Surrenders a hand, returning half of the value of the bet associated with the player's current hand
	 * @param dealer hand - hand of the dealer
	 * @param playerHand
	 * @return
	 * @throws HandIsSplitException
	 * 
	 * @see 
	 */
	public float surrender(Hand dealerHand) throws IllegalHandException{
		if(this.hand.size() != 1) throw new IllegalHandException("surrender is valid only if hand is not split");
		Hand currentHand = hand.listIterator(currHand).next();
		currentHand.surrender = true;
		if(dealerHand.getSize() == 2 && dealerHand.getScore() == 21) {
			return 0;
		}
		else
			return currentHand.curBet/2;
		
	}
	
	public boolean doubleBet() throws IllegalHandException, NotEnoughMoneyException{
		Hand h = hand.listIterator(currHand).next();
		if(hand.size() != 1 || h.getSize() != 2 || h.getScore() < 8 || h.getScore() > 12) throw new IllegalHandException();
		addPlayerMoney(-h.curBet);
		try{
			h.addBet(h.curBet);
		}catch(IllegalBetException e){
			throw new IllegalHandException(e.getMessage());
		}
		return true;
	}

	public double getPlayerMoney() {
		return playerMoney;
	}

	public void addPlayerMoney(float playerMoney) throws NotEnoughMoneyException{
			if(this.playerMoney + playerMoney < 0) throw new NotEnoughMoneyException();
			this.playerMoney += playerMoney;
	}

	public Hand getNextHand(){
		Hand h = hand.listIterator(currHand).next(); 
		if(h.busted || h.stand || h.surrender) { // means hand is not valid TODO: replace with attribute
			if(currHand >= this.hand.size()-1){
				currHand = 0;
				return null;
			}
			currHand++; //current hand not valid, increment
		}
		return hand.listIterator(currHand).next();
	}
	
	public void stand(){
		hand.listIterator(currHand).next().stand = true;
	}
	
}
