package project;

import java.util.ArrayList;

import java.util.Iterator;

public class SuperPlayer {

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
	public void hit(Shoe s){
		this.getCurrHand().addCard(s.getNext());	
	}
	
	/**
	 * Gets the hand's score, the sum of the score of every cards in the hand
	 * @param h from which to get the score
	 * @return int score of h
	 */ 
	public int getScore(Hand h) {
		int score=0;
		for(Card c : h.cards) {
			score += c.getScore();
		}
		return score;
	}
	
	/**
	 * Gets current hand of the player
	 * @return Hand
	 */
	public Hand getCurrHand(){
		return hand.listIterator(currHand).next();
	}
	
	/**
	 * Returns a textual description of the Hands
	 * @return
	 */
	public String getHands() {
		StringBuilder sb = new StringBuilder();
		int score=0;
		int i = 0;
		for (Hand h : hand){
			if(this.hand.size() > 1) sb.append("[" + i + "]"); // se tivermos mais que uma mao, mostramos o index desta
			sb.append(h.toString());
			score += h.getScore();
			i++;
		}
		sb.append(" (" + score + ")");
		
		return sb.toString();
	}
	
	/** 
	 * Returns all the cards in the hands of the player to the shoe
	 * @param s - shoe to which to return the cards
	 */
	public void resetHands(Shoe s){
		Iterator<Hand> it = this.hand.iterator();
		while(it.hasNext()){
			s.addLast(it.next().getCards());
			it.remove();
		}

		hand.add(new Hand(null, null,minBet,maxBet));
	}
	
}
