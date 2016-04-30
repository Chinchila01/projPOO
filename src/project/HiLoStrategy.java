package project;

/** HiLoStrategy class
 * 
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class HiLoStrategy {
	
	int runningCount;
	float trueCount;
	int nbDecksLeft;
	
	public HiLoStrategy() {
		runningCount = 0;
		trueCount = 0;
	}
	
	/**
	 * Table for HiLo strategy
	 * 
	 * @param rank - char indicating rank of card. T means 10.
	 * @return value of card according to the HiLo startegy.
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
			//TODO: throw exception
		}
		// should never reach here
		return 0;
	}
	
	//TODO: card type is 'N', etc, mudar para '2', '3', ... 'T', etc
	
	public void updateCount(Card newCard, int nbDecksLeft) {
		
		runningCount += newCard.getType();
		trueCount = runningCount/nbDecksLeft;
		
	}
	
	//TODO: in some cases Illustrious18 and Fab4 overlap
	
	
	public char Illustrious18(Hand playerHand, Card dealerCard) {
		
		// Insurance: Insure at +3 or higher. ??? que é que isto quer dizer ????
		// 16vT
		if(playerHand.getScore() == 16 && dealerCard.getType() == 'T') {
			return (trueCount >= 0 ? 's' : 'h');
		}
		// 15vT
		if(playerHand.getScore() == 15 && dealerCard.getType() == 'T') {
			return (trueCount >= 4 ? 's' : 'h');
		}
		// TTv5
		if(playerHand.cards.iterator().next().getType() == 'T' && playerHand.cards.iterator().next().getType() == 'T' && dealerCard.getType() == '5') {
			return (trueCount >= 5 ? 'p' : 's');
		}
		// TTv6
		if(playerHand.cards.iterator().next().getType() == 'T' && playerHand.cards.iterator().next().getType() == 'T' && dealerCard.getType() == '6') {
			return (trueCount >= 4 ? 'p' : 's');
		}
		// 10vT
		if(playerHand.getScore() == 10 && dealerCard.getType() == 'T') {
			return (trueCount >= 4 ? 'd' : 'h');
		}
		// 12v3
		if(playerHand.getScore() == 12 && dealerCard.getType() == '3') {
			return (trueCount >= 2 ? 's' : 'h');
		}
		// 12v2
		if(playerHand.getScore() == 12 && dealerCard.getType() == '2') {
			return (trueCount >= 3 ? 's' : 'h');
		}
		// 11vA
		if(playerHand.getScore() == 11 && dealerCard.getType() == 'A') {
			return (trueCount >= 1 ? 'd' : 'h');
		}
		// 9v2
		if(playerHand.getScore() == 9 && dealerCard.getType() == '2') {
			return (trueCount >= 1 ? 'd' : 'h');
		}
		// 10vA
		if(playerHand.getScore() == 10 && dealerCard.getType() == 'A') {
			return (trueCount >= 4 ? 'd' : 'h');
		}
		// 9v7
		if(playerHand.getScore() == 9 && dealerCard.getType() == '7') {
			return (trueCount >= 3 ? 'd' : 'h');
		}
		// 16v9
		if(playerHand.getScore() == 16 && dealerCard.getType() == '9') {
			return (trueCount >= 5 ? 's' : 'h');
		}
		// 13v2
		if(playerHand.getScore() == 13 && dealerCard.getType() == '2') {
			return (trueCount >= -1 ? 's' : 'h');
		}
		// 12v4
		if(playerHand.getScore() == 12 && dealerCard.getType() == '4') {
			return (trueCount >= 0 ? 's' : 'h');
		}
		// 12v5
		if(playerHand.getScore() == 12 && dealerCard.getType() == '5') {
			return (trueCount >= -2 ? 's' : 'h');
		}
		// 12v6
		if(playerHand.getScore() == 12 && dealerCard.getType() == '6') {
			return (trueCount >= -1 ? 's' : 'h');
		}
		// 13v3
		if(playerHand.getScore() == 13 && dealerCard.getType() == '3') {
			return (trueCount >= -2 ? 's' : 'h');
		}
		
		// default:
		return '0';
	}
	
	public char Fab4(Hand playerHand, Card dealerCard) {
		
		// '0' means use basic strategy
		
		// 14vT
		if(playerHand.getScore() == 14 && dealerCard.getType() == 'T') {
			return (trueCount >= -3 ? 'u' : '0');
		}
		// 15vT
		if(playerHand.getScore() == 15 && dealerCard.getType() == 'T') {
			return (trueCount >= 0 ? 'u' : '0');
		}
		// 15v9
		if(playerHand.getScore() == 15 && dealerCard.getType() == '9') {
			return (trueCount >= 2 ? 'u' : '0');
		}
		// 15vA
		if(playerHand.getScore() == 15 && dealerCard.getType() == 'A') {
			return (trueCount >= 1 ? 'u' : '0');
		}
		
		
		// default:
		return '0';
	}
	
	

}
