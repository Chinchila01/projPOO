package project;

import java.util.ArrayList;

import java.util.Iterator;

public class SuperPlayer {

	float playerMoney;
	ArrayList<Hand> hand;
	int minBet,maxBet;	
	
	/**
	 * Constructor for a Player object. Needs a object player to be created.
	 * 
	 * @param Object player
	 * @see Player Constructor
	 */
	public SuperPlayer(float initialMoney, int minBet, int maxBet){
		playerMoney=initialMoney;
		this.minBet=minBet;
		this.maxBet=maxBet;
		hand = new ArrayList<Hand>();
		hand.add(new Hand(null, null,minBet,maxBet));
	}
	
	/**
	 * Implementation of inherited method hit
	 * Gets the next card from Shoe
	 * 
	 * @param Shoe
	 * @return Card object
	 * @see hit
	 */
	public void hit(Hand h,Shoe s){
		h.addCard(s.getNext());	
	}
	
	public int getScore(Hand h) {
		int score=0;
		for(Card c : h.cards) {
			score += c.getScore();
		}
		return score;
	}
	
	public String getHands() {
		StringBuilder sb = new StringBuilder();
		int score=0;
		for (Hand h : hand){
			sb.append(h.toString());
			score += h.getScore();
		}
		sb.append(" (" + score + ")");
		
		return sb.toString();
	}
	
	public void resetHands(Shoe s){
		Iterator<Hand> it = this.hand.iterator();
		while(it.hasNext()){
			it.next();
			it.remove();
		}
		
		hand.add(new Hand(null, null,minBet,maxBet));
	}
	
}
