package project;

import java.util.ArrayList;
import java.util.Collections;

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
	/**
	 * Array with decks in order
	 * 
	 * @see Deck
	 * @see Card
	 */
	private Deck[] decks;
	private int currentDeck;
	private int totalDecks;
	
	/**
	 * Constructor for a Shoe of n decks
	 * @param n is the number of decks in the deck array. Must be between 2 and 8
	 */
	public Shoe(int n){
		if(n >= 2 && n <= 8){
			this.totalDecks = n;
			this.decks = new Deck[totalDecks];
			for(int i = 0; i < this.totalDecks; i++) this.decks[i] = new Deck();
			this.currentDeck = 0;
		}
		/*que fazer caso n seja menor que 2 ou maior que 8? 
		 * R: mandar uma mensagem de erro/exception? ass:vieira*/
	}
	
	/**
	 * Default no-arg constructor - defaults to 4 decks
	 */
	public Shoe(){
		this(4);
	}
	
	/**
	 * Gets the next available card from the shoe
	 * @return next available card from the shoe
	 */
	public Card getNext(){
		Card aux = decks[currentDeck].getNext();
		if(decks[currentDeck].isEmpty()) currentDeck++;
		return aux;
	}
	
	/**
	 * Adds a card to the bottom of the shoe
	 * @param c card to add to the bottom of the shoe
	 */
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
	
	//TODO: solucao temporaria. Acrescentar metodo getTotal no Deck.
	//		Usar o Deck apenas como classe utilitaria, i.e., no Shoe nao ter Decks
	//		mas sim um ArayList de Cards, usar o Deck apenas para gerar as cards iniciais.
	public void shuffle() {
		
		int totalCards = 0;
		for(int i=0; i<totalDecks; i++) {
			totalCards += decks[i].getTotal();
		}
		
		ArrayList<Card> cardsToShuffle = new ArrayList<Card>(totalCards);
		for(int i=0; i<totalDecks; i++) {
			cardsToShuffle.addAll(decks[i].cards);
		}
		
		Collections.shuffle(cardsToShuffle);
		
	}
	
}
