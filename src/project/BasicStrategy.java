package project;

import java.util.Iterator;

/** BasicStrategy class
 * 
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class BasicStrategy {
	
	static final String[][] hardTable = { 	
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"H", "Dh", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"},
											{"Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "H", "H"},
											{"Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "H"},
											{"H", "H", "S", "S", "S", "H", "H", "H", "H", "H"},
											{"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"},
											{"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"},
											{"S", "S", "S", "S", "S", "H", "H", "H", "Rh", "H"},
											{"S", "S", "S", "S", "S", "H", "H", "Rh", "Rh", "Rh"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}
										};
	
	static final String[][] softTable = { 	
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"S", "Ds", "Ds", "Ds", "Ds", "S", "S", "H", "H", "H"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}
										};
	
	static final String[][] pairsTable = { 	
											{"H", "H", "P", "P", "P", "P", "H", "H", "H", "H"},
											{"H", "H", "P", "P", "P", "P", "H", "H", "H", "H"},
											{"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
											{"Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "Dh", "H", "H"},
											{"H", "P", "P", "P", "P", "H", "H", "H", "H", "H"},
											{"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"},
											{"P", "P", "P", "P", "P", "P", "P", "P", "P", "P"},
											{"P", "P", "P", "P", "P", "S", "P", "P", "S", "S"},
											{"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
											{"P", "P", "P", "P", "P", "P", "P", "P", "P", "P"}
										 };
	

	static public String advise(Hand playerHand, Card dealerCard) {
		
		int x, y;
		
		// choose table to use
		if(!hasPairs(playerHand) && !hasAces(playerHand)) {	// use hard table
			
			x = playerHand.getScore() - 5; // table starts at 5
			if(dealerCard.getSymbol() != 'A')
				y = dealerCard.getScore() - 2;
			else
				y = 9;
			
			return hardTable[x][y];
			
		}else if(hasAces(playerHand) && !hasAcePair(playerHand)) {	// use soft table
			
			x = playerHand.getScore() - 13;
			if(dealerCard.getSymbol() != 'A')
				y = dealerCard.getScore() - 2;
			else
				y = 9;
			
			return softTable[x][y];
			
			
		}else if(hasPairs(playerHand)) {	// use pairs table
			Iterator<Card> it = playerHand.cards.iterator();
			Card c = it.next();
			
			if(c.getSymbol() != 'A')
				x = c.getScore() - 2;
			else
				x = 9;
			if(dealerCard.getSymbol() != 'A')
				y = dealerCard.getScore() - 2;
			else
				y = 9;
			
			return pairsTable[x][y];
		}
		
		// Should never reach here
		return "";
	}
	
	//TODO: estes metodos precisam de ser static certo?
	
	/**
	 * Checks for pairs in hand
	 * 
	 * @param hand
	 * @return
	 */
	static private boolean hasPairs(Hand hand) {
		Iterator<Card> it = hand.cards.iterator();
		if(it.next().getSymbol() == it.next().getSymbol())
			return true;
		else
			return false;
	}
	
	/**
	 * Checks for aces in hand
	 * 
	 * @param hand
	 * @return
	 */
	static private boolean hasAces(Hand hand) {
		
		for(Card c : hand.cards) {
			if(c.getSymbol() == 'A')
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks for pair of aces in hand
	 * 
	 * @param hand
	 * @return
	 */
	static private boolean hasAcePair(Hand hand) {
		Iterator<Card> it = hand.cards.iterator();
		Card c = it.next();
		if(c.getSymbol() == it.next().getSymbol() && c.getSymbol() == 'A')
			return true;
		else
			return false;
	}

}
