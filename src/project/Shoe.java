package project;

import java.io.*;
import java.util.*;

/**Shoe class
 * Contains all the decks used during a blackjack game
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
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
	private ArrayList<Card> cards;
	private int currentDeck;
	private int totalDecks;
	private int playedCards;
	
	/**
	 * Constructor for a Shoe of n decks
	 * @param n is the number of decks in the deck array. Must be between 2 and 8
	 */
	/*public Shoe(int n){
		if(n >= 2 && n <= 8){
			this.totalDecks = n;
			this.decks = new Deck[totalDecks];
			for(int i = 0; i < this.totalDecks; i++) this.decks[i] = new Deck();
			this.currentDeck = 0;
			shuffle();
		}
	}*/ 
	//TODO: add exception in case  n<2 or n>8
	public Shoe(int n){
		cards = new ArrayList<Card>();
		if(n >= 2 && n <= 8){
			this.totalDecks = n;
			//this.decks = new Deck[totalDecks];
			cards.addAll((new Deck()).cards);
		}
		shuffle();
	}
	
	/**
	 * Default no-arg constructor - defaults to 4 decks
	 */
	public Shoe(){
		this(4);
	}
	
	/**
	 * Constructor for a shoe taken from a shoe file
	 * 
	 * @params shoeFile String of path of shoefile
	 */
	public Shoe(String shoefile) throws FileNotFoundException,NotParseableException{
		int nbCards = 0;
		Scanner s = new Scanner(new File(shoefile));
		cards = new ArrayList<Card>();
		while(s.hasNext()){
			this.cards.add(new Card(s.next())); //TODO: add constructor to Card with String
			nbCards++;
		}
		this.totalDecks = nbCards/52; //52 is the number of cards in a complete deck
		s.close();
	}
	
	/**
	 * Gets the next available card from the shoe
	 * @return next available card from the shoe
	 */
	/*public Card getNext(){
		Card aux = decks[currentDeck].getNext();
		if(decks[currentDeck].isEmpty()) currentDeck++;
		return aux;
	}*/
	public Card getNext(){
		return cards.remove(0);
	}
	
	/**
	 * Adds a card to the bottom of the shoe
	 * @param c card to add to the bottom of the shoe
	 */
	/*public void addLast(Card c){
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
		playedCards++; //number of cards in shoe that were already played increases
	}*/
	public void addLast(Card c){
		cards.add(c);
	}
	
	public void addLast(ArrayList<Card> ca){
		cards.addAll(ca);
	}
	
	/*
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.totalDecks; i++){
			sb.append(decks[i].toString());
		}
		return sb.toString();
	}*/
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Card c : cards) sb.append(c);
		return sb.toString();
	}
	
	/**
	 * Returns whether the shoe is full (all the decks are full)
	 * @return isFull
	 */
	/*
	public boolean isFull(){
		return decks[0].isFull();
	}
	*/
	public boolean isFull(){
		return (cards.size() == 52*totalDecks);
	}
	
	/**
	 * Shuffles the decks in the shoe
	 */
	//TODO: solucao temporaria. Acrescentar metodo getTotal no Deck.
	//		Usar o Deck apenas como classe utilitaria, i.e., no Shoe nao ter Decks
	//		mas sim um ArayList de Cards, usar o Deck apenas para gerar as cards iniciais.
	/*public void shuffle() {
		
		int totalCards = 0;
		for(int i=0; i<totalDecks; i++) {
			totalCards += decks[i].getTotal();
		}
		
		ArrayList<Card> cardsToShuffle = new ArrayList<Card>(totalCards);
		for(int i=0; i<totalDecks; i++) {
			cardsToShuffle.addAll(decks[i].cards);
		}
		System.out.println("shuffling the shoe...");
		Collections.shuffle(cardsToShuffle);
		
	}*/
	public void shuffle(){
		System.out.println("Shuffling the shoe...");
		Collections.shuffle(cards);
	}
	 
	/**
	 * Shuffles the decks in the shoe if the percentage of cards played is higher than the percentage given as a parameter
	 * @param shufflePercentage percentage of played shoe required to shuffle the deck 
	 */
	public void shuffle(int shufflePercentage){
		if(playedCards/(totalDecks*52) > shufflePercentage || playedCards/(totalDecks*52) == 100) shuffle();
	}
	
	public int getNbDecks(){
		return this.totalDecks;
	}
	
	/**
	 * Returns the number of decks remaining. This value is a 
	 * float in the range [0 totalDecks].
	 * @return decks remaining
	 */
	public float getDecksLeft(){
		return ((totalDecks*52)-playedCards)/52;
	}
}
