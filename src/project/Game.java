package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Game {

	private JFrame frmBlackjack;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PlayingArea pa = null;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frmBlackjack.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
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
		
		String cmd;
		
		while(pa.hasNextCommand()) {
			
			while(pa.player.getNextHand() != null) {	// player's turn
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
					pa.executePlayerAction(cmd);
				} catch (IllegalCmdException e) {
					System.out.println(e.getMessage());
				}
			}//end_player_turn
			
			//dealer turn
			pa.dealerTurn();
			
			//payout time
			pa.payOut();
			
			//reset hands
			pa.prepareNextRound();
			
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
		frmBlackjack = new JFrame();
		frmBlackjack.setTitle("BlackJack");
		frmBlackjack.getContentPane().setBackground(new Color(0, 128, 0));
		frmBlackjack.setBounds(100, 100, 600, 400);
		frmBlackjack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBlackjack.getContentPane().setLayout(new BoxLayout(frmBlackjack.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		frmBlackjack.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		textField = new JTextField();
		textField.setToolTipText("Minimum Bet");
		panel.add(textField);
		textField.setColumns(10);
	}
	


}
