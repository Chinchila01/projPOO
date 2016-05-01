package project;

public class DifferentCardsException extends Exception{

		public DifferentCardsException(){
			super("Cards in hand are not equal");
		}
		
		public DifferentCardsException(String s){
			super(s);
		}
}
