package project;

import java.util.ArrayList;
import java.util.ListIterator;

/** Deck Class
 * This class contains all methods that operate/are related to the Deck object<br>
 * A {@link Deck} is composed of 52 cards: <br>
 * Number cards: 2,3,4,5,6,7,8,9,10<br>
 * Face cards: A,J,Q,K<br>
 * <br>
 * The deck has each of the above cards in each of the 4 suits: Clubs, Diamonds, Spades, Hearts
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
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
	
	/**
	 * Total number of cards currently in the {@link Deck}
	 * 
	 * @see Deck
	 */
	private int total;
	private boolean isEmpty;
	private boolean isFull;
	
	/**
	 * <p>Constructs a Deck object with 52 cards.</p>
	 * <p>Consists of each of the following cards in the 4 {@link suits} available: <br>
	 * 	Number cards: 2,3,4,5,6,7,8,9,10 <br>
	 *  Face cards: A, J, Q, K </p>
	 */
	public Deck(){
		cards = new ArrayList<Card>();
		//Creating number cards
		for(int i = 0; i < 8; i++){
			for(int j= 0; j < suits.length; j++){
				cards.add(new Card(Card.suits[j],Card.symbols[i],i+2));
				total++;
			}
		}
		//Creating tens
		for(int j = 0; j < suits.length; j++){
				cards.add(new Card(Card.suits[j],Card.symbols[8],10)); 
				total++;
		}
		//Creating jacks
		for(int j = 0; j < suits.length; j++){
			cards.add(new Card(Card.suits[j],Card.symbols[9],10)); 
			total++;
		}
		//Creating queens
		for(int j = 0; j < suits.length; j++){
			cards.add(new Card(Card.suits[j],Card.symbols[10],10));
			total++;
		}
		//Creating kings
		for(int j = 0; j < suits.length; j++){
			cards.add(new Card(Card.suits[j],Card.symbols[11],10));
			total++;
		}
		//Creating aces
		for(int j = 0; j < suits.length; j++){
				cards.add(new Card(Card.suits[j],Card.symbols[12],11)); 
				total++;
		}
		
		isEmpty = false;
		isFull  = true;
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
		if(total == 0) isEmpty = true;
		if(total < 52) isFull = false;
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
		if(total != 0) isEmpty = false;
		if(total == 52) isFull = true;
	}
	
	/**
	 * Returns true if the deck has 0 cards
	 * @return isEmpty 
	 */
	public boolean isEmpty(){
		return this.isEmpty;
	}

	/**
	 * Returns true if the deck has 52 cards
	 * @return isFull
	 */
	public boolean isFull(){
		return this.isFull;
	}
	
	/**
	 * Gets the number of cards in deck
	 * @return number of cards in deck
	 */
	public int getTotal(){
		return total;
	}
	
	/**
	 * Prints deck's cards
	 * @return String of cards
	 */
	public String toString(){
		ListIterator<Card> it = cards.listIterator();
		StringBuilder sb = new StringBuilder();
		while(it.hasNext()){
			sb.append(it.next().toString());
			if(it.hasNext()) sb.append(",");
		}
		return sb.toString();
	}
}
