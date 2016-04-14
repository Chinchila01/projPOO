package project;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		
		System.out.println(deck.toString());
		
		System.out.println("Next card: " + deck.getNext());
		
		System.out.println("Deck state:");
		System.out.println(deck.toString());
		
		deck.addLast(new Card(0,4,10));

		System.out.println("Deck state:");
		System.out.println(deck.toString());
		
	}

}
