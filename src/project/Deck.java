package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/** Deck Class
* Class description  
*
* @author Filipe Correia
* @author Helder Duarte
* @author Joao Vieira
* @version 1.0
*/
public class Deck {
	ArrayList<Card> cards;
	/** vector suits 
	 * C: Clubs
	 * D: Diamonds 
	 * S: Spades
	 * H: Hearts */
	private static final char[] suits = new char[] {'C','D','S','H'};
	//private static final char[] types = new char[] {'N','J','K','Q','A'};
	//private int[] nCardsPerValue;
	//private int[] nCardsPerSuit;
	private int total;
	
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
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	//Incomplete - Decrement nCardsPerValue and nCardsPerSuit
	public Card getNext(){
		ListIterator<Card> iterator = cards.listIterator();
		Card aux = iterator.next();
		iterator.remove();
		total--;
		return aux;
	}
	
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
