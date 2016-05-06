package project;

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
											{"H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"},
											{"H", "H", "H", "Dh", "Dh", "H", "H", "H", "H", "H"},
											{"H", "H", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"},
											{"H", "H", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"},
											{"H", "Dh", "Dh", "Dh", "Dh", "H", "H", "H", "H", "H"},
											{"S", "Dh", "Dh", "Dh", "Dh", "S", "S", "H", "H", "H"},
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
	
	//TODO: throw exception quando passa dos limites da tabela
	//TODO: nao percorrer a tabela quando o score é baixo (ex: a primeira entrada da tabela hard começa com o score a 5)
	
	public String advise(Hand playerHand, Card dealerCard) {
		
		int x, y;
		
		// choose table to use
		if(!hasPairs(playerHand) && !hasAces(playerHand)) {	// use hard table
			
			x = playerHand.getScore() - 17;
			if(dealerCard.getSymbol() != 'A')
				y = dealerCard.getScore() - 1;
			else
				y = 10;
			
			return hardTable[x][y];
			
		}else if(hasAces(playerHand) && !hasAcePair(playerHand)) {	// use soft table
			
			x = playerHand.getScore() - 9;
			if(dealerCard.getSymbol() != 'A')
				y = dealerCard.getScore() - 1;
			else
				y = 10;
			
			return softTable[x][y];
			
			
		}else if(hasPairs(playerHand)) {	// use pairs table
			
			if(playerHand.cards.iterator().next().getSymbol() != 'A')
				x = playerHand.cards.iterator().next().getScore() - 1;
			else
				x = 10;
			if(dealerCard.getSymbol() != 'A')
				y = dealerCard.getScore() - 1;
			else
				y = 10;
			
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
	private boolean hasPairs(Hand hand) {
		
		if(hand.cards.iterator().next().getSymbol() == hand.cards.iterator().next().getSymbol())
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
	private boolean hasAces(Hand hand) {
		
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
	private boolean hasAcePair(Hand hand) {
		if(hand.cards.iterator().next().getSymbol() == hand.cards.iterator().next().getSymbol() && hand.cards.listIterator(0).next().getSymbol() == 'A')
			return true;
		else
			return false;
	}

}
