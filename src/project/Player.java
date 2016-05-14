package project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Player Class
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 */
public class Player implements PlayerInterface{
	
	
	 float playerMoney;
	 ArrayList<Hand> hand;
	 int minBet,maxBet;
	 int currHand;
	 
	/**
	 * Constructor for a Player object. Needs a object player to be created.
	 * 
	 * @param Object player
	 * @see Player Constructor
	 */
	public Player(float initialMoney, int minBet, int maxBet){
		playerMoney=initialMoney;
		this.minBet=minBet;
		this.maxBet=maxBet;
		hand = new ArrayList<Hand>();
		hand.add(new Hand(null, null,minBet,maxBet));
	}

	/**
	 * Split method:
	 * When you get two starting cards of the same face value, you have the option to split the hand 
	 * in two. You place another bet of the same size as the original bet and play on with two hands.
	 * 
	 * @param Hand h to be split 
	 * @param Shoe s, from which the new card will be picked through hit method
	 */
	@Override
	public void split(Hand h, Shoe s) throws IllegalHandException, NotEnoughMoneyException{
		int tempIndex = currHand;
		if(h.getSize() != 2) throw new IllegalHandException("hand is too big");
		else{
				ListIterator<Card> iterator = h.getCards().listIterator();
				Card aux = iterator.next();
				Card aux2 = iterator.next();
				
				if(!aux.equals(aux2)) throw new IllegalHandException("cards are not equal");
				this.addPlayerMoney(-h.curBet); //check if player has enough money
				
				iterator.remove();//remove last (repeated) card from hand
				hit(s);//get a card from shoe
				
				Hand newHand;
				newHand = new Hand(aux2,null,minBet,maxBet);
				hand.add(newHand);
				//temporarily change currHand to new hand
				currHand = hand.size()-1;
				hit(s); //Immediately get a new card for newHand
				currHand = tempIndex; //putting it back
				newHand.curBet=h.curBet; //set the same bet for newHand
				
		}
	}

	@Override
	public void insurance(Hand dealerHand) throws IllegalHandException{
			//only check the card that is initially faced up
			if (dealerHand.getCards().iterator().next().getSymbol()!='A') throw new IllegalHandException("dealer does not have an Ace");
			Hand playerHand = hand.listIterator(currHand).next();
			if(playerHand.getSize() != 2) throw new IllegalHandException("not at the beginning of the hand");
			if(playerHand.surrenderDone) throw new IllegalHandException("player has already surrendered");
			if(playerHand.standDone) throw new IllegalHandException("player has standed, insurance not available");
			playerHand.insuranceDone = true;//insurance bet 
	}
	
	@Override
	public float surrender(Hand dealerHand) throws IllegalHandException{
		if(this.hand.size() != 1) throw new IllegalHandException("surrender is valid only if hand is not split");
		Hand currentHand = hand.listIterator(currHand).next();
		currentHand.surrenderDone = true;
		//No late surrender
		if(dealerHand.getSize() == 2 && dealerHand.getScore() == 21) return 0;
		else return currentHand.curBet/2;
		
	}
	
	@Override
	public boolean doubleBet() throws IllegalHandException, NotEnoughMoneyException{
		Hand h = hand.listIterator(currHand).next();
		System.out.println(h.curBet);
		if(!h.doubleAvailable()) 
			throw new IllegalHandException();
		addPlayerMoney(-h.curBet);
		try{
			h.addBet(h.curBet);
		}catch(IllegalCmdException e){
			addPlayerMoney(h.curBet);
			throw new IllegalHandException(e.getMessage());
		}
		h.doubleDone=true;
		return true;
		
	}
	

	@Override
	public double getPlayerMoney() {
		return playerMoney;
	}
	
	@Override
	public void addPlayerMoney(float playerMoney) throws NotEnoughMoneyException{
			if(this.playerMoney + playerMoney < 0) throw new NotEnoughMoneyException();
			this.playerMoney += playerMoney;
	}
	
	@Override
	public Hand getNextHand(){
		Hand h = hand.listIterator(currHand).next(); 
		if(h.busted || h.standDone || h.surrenderDone) { // means hand is not valid
			if(currHand >= this.hand.size()-1){
				currHand = 0;
				return null;
			}
			currHand++; //current hand not valid, increment
			System.out.println("playing " + (currHand+1) + ((currHand+1)==1 ? "st" :( (currHand+1)==2 ? "nd" : ((currHand+1)==3 ? "rd" : "th"))) + " hand...");
		}
		
		return hand.listIterator(currHand).next();
	}
	
	@Override
	public void stand(){
		hand.listIterator(currHand).next().standDone = true;
	}
	
	@Override
	public Hand getCurrHand() {
		return hand.listIterator(currHand).next();
	}
	
	@Override
	public Card hit(Shoe s) {
		Hand h = getCurrHand();
		Card c = s.getNext();
		if(c.getSymbol() == 'A' && h.getScore() > 10) 
			c.setScore(1);
		
		for(Card ca : h.getCards()){
			if(ca.getSymbol() == 'A') {
				if((c.getScore() + h.getScore()) < 21) break;
				ca.setScore(1); 		
			}
		}
		
		h.addCard(c);
		return c;
	}
	
	@Override
	public String getHand() {
		StringBuilder sb = new StringBuilder();
		Hand h = hand.listIterator(currHand).next();
		if(this.hand.size() > 1) sb.append("[" + (currHand+1) + "] "); // se tivermos mais que uma mao, mostramos o index desta
		sb.append(h.toString());
		sb.append(" (" + h.getScore() + ")\n");
		
		return sb.toString();
	}
	
	/**
	 * This method removes player's cards from his hand and
	 * adds them to the bottom of the shoe.
	 */
	@Override
	public void resetHands(Shoe s) {
		Iterator<Hand> it = this.hand.iterator();
		while(it.hasNext()){
			s.addLast(it.next().getCards());
			it.remove();
		}
	
		hand.add(new Hand(null, null,minBet,maxBet));
	}
	
	/**
	 * Check if split can to be executed
	 * @return true if success
	 */
	public boolean splitAvailable(){
		return (hand.size()<4 && getCurrHand().getSize()==2 && getCurrHand().cardsEqual());
	}

	/**
	 * Check if player has busted hands
	 * @return true if success
	 */
	public boolean hasBustedHands(){
		for(Hand h : hand){
			if (h.busted==true)
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "player's hand " + getHand();
	}

	
}
