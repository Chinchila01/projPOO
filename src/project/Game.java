package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;

public class Game {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PlayingArea pa = null;
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		if(args.length < 1){ // minimum arg size
			System.out.println("Not enough arguments");
			System.exit(-1);
		}
		
		//Argument variables
		int minBet, maxBet, nbDecks, shufflePercentage;
		float initialMoney;
		
		//TODO: do argument checking
		switch(args[0].charAt(1)) {
		case 'i':
			//Checking arguments
			if(args.length < 6){
				System.out.println("Not enough arguments");
				System.exit(1);
			}
			minBet = Integer.parseInt(args[1]);
			maxBet = Integer.parseInt(args[2]);
			initialMoney = Float.parseFloat(args[3]);
			nbDecks = Integer.parseInt(args[4]);
			shufflePercentage = Integer.parseInt(args[5]);
			
			pa = new PlayingAreaInteractive(minBet, maxBet, initialMoney, nbDecks, shufflePercentage);
			break;
		case 'd':
			//Checking arguments
			if(args.length < 6){
				System.out.println("Not enough arguments");
				System.exit(1);
			}
			minBet = Integer.parseInt(args[1]);
			maxBet = Integer.parseInt(args[2]);
			initialMoney = Float.parseFloat(args[3]);
			String shoefile = args[4];
			String cmdFile = args[5];
			
			pa = new PlayingAreaDebug(minBet, maxBet, initialMoney, shoefile, cmdFile);
			break;
		case 's':
			//Checking arguments
			if(args.length < 8){
				System.out.println("Not enough arguments");
				System.exit(1);
			}
			minBet = Integer.parseInt(args[1]);
			maxBet = Integer.parseInt(args[2]);
			initialMoney = Float.parseFloat(args[3]);
			nbDecks = Integer.parseInt(args[4]);
			shufflePercentage = Integer.parseInt(args[5]);
			int nbShuffles = Integer.parseInt(args[6]);
			String strategy =args[7];
			
			pa = new PlayingAreaSimulation(minBet, maxBet, initialMoney, nbDecks, shufflePercentage, nbShuffles, strategy);
			break;
		default: System.exit(-1);
		}

		//PlayingAreaDebug pa = new PlayingAreaDebug(args);
		Player player = new Player(pa.initialMoney, pa.minBet, pa.maxBet);
		Dealer dealer = new Dealer(pa.initialMoney, pa.minBet, pa.maxBet);
		
		String cmd;
		
		while(pa.hasNextCommand()) {
			
			while(player.getNextHand() != null) {	// player's turn
				cmd = "";
				while(cmd.equals("")){
					try{
						cmd = pa.getCommand();	//get player input
					}catch(NoMoreCmdsException e){
						System.out.println("Game Over");
						pa.quit();
					}
				}
				
				try {
					pa.executePlayerAction(cmd, player, dealer);
				} catch (IllegalCmdException e) {
					System.out.println(e.getMessage());
				}
			}//end_player_turn
			
			//dealer turn
			pa.dealerTurn(dealer);
			
			//payout time
			pa.payOut(player, dealer);
			
			//reset hands
			pa.prepareNextRound(player,dealer);
			
		}//end_rounds
			
	}

	/**
	 * Create the application.
	 */
	public Game() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	}
	


}
