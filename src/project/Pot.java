package project;

import java.util.ArrayList;
import java.util.ListIterator;


/** Pot Class
 * A pot has a total value and a number of chips.
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class Pot {
	
	ArrayList<Chip> chips;
	int totalValue;
	
	/** Constructor for a Pot object
	 * 
	 * @param totalValue is the total value the pot will have.
	 * The chips are constructed according to this value.
	 */
	public Pot(int totalValue) {
		
		chips = new ArrayList<Chip>();
		this.totalValue = totalValue;
		int i;
		
		for(i=0; i<totalValue/100; i++) {
			chips.add(new Chip(100));
		}
		totalValue = totalValue - totalValue/100*100;
		for(i=0; i<totalValue/25; i++) {
			chips.add(new Chip(25));
		}
		totalValue = totalValue - totalValue/25*25;
		for(i=0; i<totalValue/5; i++) {
			chips.add(new Chip(5));
		}
		totalValue = totalValue - totalValue/5*5;
		for(i=0; i<totalValue; i++) {
			chips.add(new Chip(1));
		}
		
	}
	
	@Override
	public String toString(){
		ListIterator<Chip> it = this.chips.listIterator();
		StringBuilder sb = new StringBuilder();
		
		while(it.hasNext()){
			sb.append(it.next().toString());
		}
		return sb.toString();
	}

}
