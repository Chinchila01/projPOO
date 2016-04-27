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
		hand = new Hand(deck.getNext(),deck.getNext(),10,100);
		System.out.println(hand.toString());
		
		System.out.println("Deck state:");
		System.out.println(deck.toString());
		
		Shoe shoe1 = new Shoe(3);
		
		System.out.println("Shoe: ");
		System.out.println(shoe1.toString());
		System.out.println(shoe1.isFull());
		shoe1.addLast(shoe1.getNext());
		System.out.println(shoe1.isFull());
		System.out.println(shoe1.toString());
		
		Pot pot = new Pot(1457);
		System.out.println(pot);
	}

}
