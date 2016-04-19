package project;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		Hand hand;
		
		System.out.println(deck.toString());
		
		System.out.println("Next card: " + deck.getNext());
		
		System.out.println("Deck state:");
		System.out.println(deck.toString());
		
		deck.addLast(deck.getNext());

		System.out.println("Deck state:");
		System.out.println(deck.toString());
		
		System.out.println("Creating a hand:");
		hand = new Hand(deck.getNext(),deck.getNext());
		System.out.println(hand.toString());
		
		System.out.println("Deck state:");
		System.out.println(deck.toString());
	}

}
