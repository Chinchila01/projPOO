package project;

/**
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class PlayingAreaDebug extends PlayingArea {
	
	String shoeFile;
	String cmdFile;
	
	int handIndex;
	int previousBet;
	public static int minimumBet;
	
	public PlayingAreaDebug(String[] args) {
		
		super(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		if(args.length != 6) {
			System.out.println("Invalid number of arguments for debug mode.");
			System.out.println("max-bet min-bet balance shoe-file shuffle-file");
			System.exit(-1);
		}
		this.shoeFile = args[4];
		this.cmdFile = args[5];
		
		previousBet = minBet;
	}

}
