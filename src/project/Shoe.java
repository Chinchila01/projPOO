package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/**Shoe class
 * Contains all the decks used during a blackjack game
 * 
 * @author Filipe Correia
 * @author Hélder Duarte
 * @author João Vieira
 *
 * @see Deck
 * @see Card
 */
public class Shoe {
	private Deck[] decks;
	private int currentDeck;
	private int totalDecks;
	
	public Shoe(int n){
		if(n >= 2 && n <= 8){
			this.totalDecks = n;
			this.decks = new Deck[totalDecks];
			for(int i = 0; i < this.totalDecks; i++) this.decks[i] = new Deck();
			this.currentDeck = 0;
		}
		/*que fazer caso n seja menor que 2 ou maior que 8? */
	}
	
	public Card getNext(){
		Card aux = decks[currentDeck].getNext();
		if(decks[currentDeck].isEmpty()) currentDeck++;
		return aux;
	}
	
	public void addLast(Card c){
		Card aux;
		Card aux2 = c;
		
		//if last deck is full we need to move the first card to the next deck and add at the last deck
		if(decks[totalDecks-1].isFull()){
			for(int i = totalDecks-1; i > 0; i--){
				if(decks[i].isFull()){
					aux = decks[i].getNext();
					decks[i].addLast(aux2);
					aux2 = aux;
				}
			}
			decks[0].addLast(aux2);
		}
		else decks[totalDecks-1].addLast(c);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.totalDecks; i++){
			sb.append(decks[i].toString());
		}
		return sb.toString();
	}
	
	public boolean isFull(){
		return decks[0].isFull();
	}
	
}
