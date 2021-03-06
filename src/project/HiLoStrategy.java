package project;

/** Hi-Lo Strategy class
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class HiLoStrategy {
	
	int runningCount = 0;
	float trueCount = 0;
	float nbDecksLeft = 0;
	
	/**
	 * Constructor for the Hi-lo Strategy class
	 */
	public HiLoStrategy() {
		runningCount = 0;
		trueCount = 0;
	}
	
	/**
	 * Table for HiLo strategy
	 * 
	 * @param rank - char indicating rank of card. T means 10.
	 * @return value of card according to the HiLo strategy.
	 */
	public int getValue(char rank) {
		
		switch(rank) {
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
			return 1;
		case '7':
		case '8':
		case '9':
			return 0;
		case 'T':
		case 'J':
		case 'Q':
		case 'K':
		case 'A':
			return -1;
		default:
		}
		// should never reach here
		return 0;
	}
	
	/**
	 * Update running and true count
	 * @param newCard
	 * @param nbDecksLeft
	 */
	public void updateCount(Card newCard, float nbDecksLeft) {
		runningCount += getValue(newCard.getSymbol());
		trueCount = runningCount/nbDecksLeft;
	}
	
	/**
	 * Depending on player's and dealer's cards get the advised move to be played,
	 * accordingly to this strategy
	 * @param playerHand
	 * @param dealerCard
	 * @param canInsure
	 * @return
	 */
	public char getStrat(Hand playerHand, Card dealerCard, boolean canInsure) {
		/**
		 * Illustrious18
		 */
		if(canInsure && trueCount>=3) return 'i';
		// 16vT
		if(playerHand.getScore() == 16 && dealerCard.getSymbol() == 'T') {
			return (trueCount >= 0 ? 's' : 'h');
		}
		// 15vT
		if(playerHand.getScore() == 15 && dealerCard.getSymbol() == 'T') {
			// Illustrious18 	return (trueCount >= 4 ? 's' : 'h');
			// Fab4 			return (trueCount >= 0 ? 'u' : '0');
			if(trueCount>0 && trueCount<3) return 'u';
			if(trueCount>=4) return 's';
			return 'h';
		}
		// TTv5
		if(playerHand.cards.iterator().next().getSymbol() == 'T' && playerHand.cards.iterator().next().getSymbol() == 'T' && dealerCard.getSymbol() == '5') {
			return (trueCount >= 5 ? 'p' : 's');
		}
		// TTv6
		if(playerHand.cards.iterator().next().getSymbol() == 'T' && playerHand.cards.iterator().next().getSymbol() == 'T' && dealerCard.getSymbol() == '6') {
			return (trueCount >= 4 ? 'p' : 's');
		}
		// 10vT
		if(playerHand.getScore() == 10 && dealerCard.getSymbol() == 'T') {
			return (trueCount >= 4 ? 'd' : 'h');
		}
		// 12v3
		if(playerHand.getScore() == 12 && dealerCard.getSymbol() == '3') {
			return (trueCount >= 2 ? 's' : 'h');
		}
		// 12v2
		if(playerHand.getScore() == 12 && dealerCard.getSymbol() == '2') {
			return (trueCount >= 3 ? 's' : 'h');
		}
		// 11vA
		if(playerHand.getScore() == 11 && dealerCard.getSymbol() == 'A') {
			return (trueCount >= 1 ? 'd' : 'h');
		}
		// 9v2
		if(playerHand.getScore() == 9 && dealerCard.getSymbol() == '2') {
			return (trueCount >= 1 ? 'd' : 'h');
		}
		// 10vA
		if(playerHand.getScore() == 10 && dealerCard.getSymbol() == 'A') {
			return (trueCount >= 4 ? 'd' : 'h');
		}
		// 9v7
		if(playerHand.getScore() == 9 && dealerCard.getSymbol() == '7') {
			return (trueCount >= 3 ? 'd' : 'h');
		}
		// 16v9
		if(playerHand.getScore() == 16 && dealerCard.getSymbol() == '9') {
			return (trueCount >= 5 ? 's' : 'h');
		}
		// 13v2
		if(playerHand.getScore() == 13 && dealerCard.getSymbol() == '2') {
			return (trueCount >= -1 ? 's' : 'h');
		}
		// 12v4
		if(playerHand.getScore() == 12 && dealerCard.getSymbol() == '4') {
			return (trueCount >= 0 ? 's' : 'h');
		}
		// 12v5
		if(playerHand.getScore() == 12 && dealerCard.getSymbol() == '5') {
			return (trueCount >= -2 ? 's' : 'h');
		}
		// 12v6
		if(playerHand.getScore() == 12 && dealerCard.getSymbol() == '6') {
			return (trueCount >= -1 ? 's' : 'h');
		}
		// 13v3
		if(playerHand.getScore() == 13 && dealerCard.getSymbol() == '3') {
			return (trueCount >= -2 ? 's' : 'h');
		}
		
		/**
		 * Fab4
		 */
		// '0' means use basic strategy
		// 14vT
		if(playerHand.getScore() == 14 && dealerCard.getSymbol() == 'T') {
			return (trueCount >= -3 ? 'u' : '0');
		}
		// 15v9
		if(playerHand.getScore() == 15 && dealerCard.getSymbol() == '9') {
			return (trueCount >= 2 ? 'u' : '0');
		}
		// 15vA
		if(playerHand.getScore() == 15 && dealerCard.getSymbol() == 'A') {
			return (trueCount >= 1 ? 'u' : '0');
		}
				
		
		// default:
		return '0';
	}
	
	/**
	 * reset Hi-lo Strategy counts
	 */
	public void reset(){
		runningCount = 0;
		trueCount = 0;
	}
	

}
