package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/** Deck Class
 * This class contains all methods that operate/are related to the Deck object
 * 
 * @author Filipe Correia
 * @author Hélder Duarte
 * @author João Vieira
 * @version 1.0
 */
public class Deck {
	/**
	 * Array where the cards from the {@link Deck} are stored, in order
	 * 
	 * @see Deck
	 */
	ArrayList<Card> cards;
	/**
	 * Available suits to be used on the {@link Deck}
	 * 
	 * @see Deck
	 */
	private static final char[] suits = new char[] {'C','D','S','H'};
	//private static final char[] types = new char[] {'N','J','K','Q','A'};
	//private int[] nCardsPerValue;
	//private int[] nCardsPerSuit;
	/**
	 * Total number of cards currently in the {@link Deck}
	 * 
	 * @see Deck
	 */
	private int total;
	
	/**
	 * <p>Constructs a Deck object with 52 cards.</p>
	 * <p>Consists of each of the following cards in the 4 {@link suits} available: <br>
	 * 	Number cards: 2,3,4,5,6,7,8,9,10 <br>
	 *  Face cards: A, J, Q, K </p>
	 * 
	 * @author Filipe Correia
	 * @author Hélder Duarte
	 * @author João Vieira
	 */
	public Deck(){
		//nCardsPerValue = new int[13];
		//nCardsPerSuit = new int[4];
		cards = new ArrayList<Card>();
		//Creating number cards
		for(int i = 2; i <= 10; i++){
			for(int j= 0; j < suits.length; j++){
				cards.add(new Card(j,0,i));
				//nCardsPerValue[i-1] += 1;
				//nCardsPerSuit[j] += 1;
				total++;
			}
		}
		//Creating aces
		for(int j = 0; j < suits.length; j++){
				cards.add(new Card(j,4,11)); 
				//nCardsPerValue[0] += 1; 
				//nCardsPerSuit[j] += 1;
				total++;
		}
		//Creating jacks
		for(int j = 0; j < suits.length; j++){
			cards.add(new Card(j,1,10)); 
			//nCardsPerValue[10] += 1; 
			//nCardsPerSuit[j] += 1;
			total++;
		}
		//Creating queens
		for(int j = 0; j < suits.length; j++){
			cards.add(new Card(j,3,10));
			//nCardsPerValue[11] += 1;
			//nCardsPerSuit[j] += 1;
			total++;
		}
		//Creating kings
		for(int j = 0; j < suits.length; j++){
			cards.add(new Card(j,2,10));
			//nCardsPerValue[12] += 1;
			//nCardsPerSuit[j] += 1;
			total++;
		}
		//Shuffling
		shuffle();
	}
	
	/**
	 * Uses the {@link Collections.shuffle} method to shuffle the {@link Deck}
	 * 
	 * @return void
	 * @see Deck
	 * @see Collections.shuffle 
	 */
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	/**
	 * Gets the card on top of the {@link Deck}
	 * 
	 * @return Card
	 * @see Deck
	 * @see Card
	 */
	public Card getNext(){
		ListIterator<Card> iterator = cards.listIterator();
		Card aux = iterator.next();
		iterator.remove();
		total--;
		return aux;
	}
	
	/**
	 * Adds a {@link Card} to the bottom of the {@link Deck}
	 * 
	 * @param c {@link Card} object to be added to the {@link Deck}
	 * @return void
	 * @see Deck
	 * @see Card
	 * @see Deck.getNext
	 */
	public void addLast(Card c){
		cards.add(total,c);
		total++;
	}
	
	/*
	public boolean validate(Card c){
		switch(c.getSymbol()) {
		case 'N': if(nCardsPerValue[c.getScore()] == 4) return false; break;
		case 'K': if(nCardsPerValue[12] == 4) return false; break;
		case 'Q': if(nCardsPerValue[11] == 4) return false; break;
		case 'J': if(nCardsPerValue[10] == 4) return false; break;
		default: return false;
		}
		
		if(nCardsPerSuit[c.getSuit()] == 13) return false;
		
		return true;
	}*/
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Card c : cards){
			sb.append(c.toString());
			sb.append(",");
		}
		return sb.toString();
	}
}
