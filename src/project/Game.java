package project;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;

import java.awt.GridLayout;

import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class Game {

	private JFrame frmBlackjack;
	private JTextField txtMinimumBet;
	private JTextField txtMaximumBet;
	private JTextField txtBalance;
	private JTextField txtShoe;
	private JTextField txtShuffle;
	private JButton btnPlay;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel dealerArea;
	private JPanel playingAreaPanel;
	private JPanel chipsArea;
	private JPanel commandArea;
	private JPanel chips;
	private JPanel dealerCards;
	private JTextField txtDealerpoints;
	private JPanel playerCards;
	private JTextField txtPlayerPoints;
	private JTextField pot;
	private JButton btnChipBlack;
	private JButton btnChipWhite;
	private JButton btnChipGreen;
	private JButton btnChipRed;
	private JButton btnCard;
	private JButton btnBet;
	private JButton btnDeal;
	private JButton btnHit;
	private JButton btnStand;
	private JButton btnInsurance;
	private JButton btnDouble;
	private JButton btnSplit;
	private JButton btnSurrender;
	private JButton btnQuit;
	private JPanel menu;
	private JPanel game;
	
	//Argument variables
	int minBet, maxBet, nbDecks, shufflePercentage;
	float initialMoney;
	String cmdBuffer;
	
	PlayingAreaGUI pag;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		PlayingArea pa = null;
		
		if(args.length < 1){ // minimum arg size
			System.out.println("Not enough arguments");
			System.exit(-1);
		}
		
		
		//Argument variables
		int minBet, maxBet, nbDecks, shufflePercentage;
		float initialMoney;
		
		
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
			
		case 'g':

			//EventQueue.invokeLater(new Runnable() {
				//public void run() {
					try {
						Game window = new Game();
						window.frmBlackjack.setVisible(true);
						window.createDialog();
						minBet = Integer.parseInt(window.txtMinimumBet.getText());
						maxBet = Integer.parseInt(window.txtMaximumBet.getText());
						initialMoney = Integer.parseInt(window.txtBalance.getText());
						nbDecks = Integer.parseInt(window.txtShoe.getText());
						shufflePercentage = Integer.parseInt(window.txtShuffle.getText());
						pa = new PlayingAreaGUI(minBet,maxBet,initialMoney,nbDecks,shufflePercentage,null,null,window.frmBlackjack);
						window.createGamePanel((PlayingAreaGUI)pa);
					} catch (Exception e) {
						e.printStackTrace();
					}
				//}
			//});
			
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
		createFrame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void createFrame() {
		
		frmBlackjack = new JFrame();
		frmBlackjack.setTitle("BlackJack");
		frmBlackjack.getContentPane().setBackground(new Color(0, 128, 0));
		frmBlackjack.setBounds(100, 100, 1280, 720);
		frmBlackjack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmBlackjack.getContentPane().setLayout(new BoxLayout(frmBlackjack.getContentPane(), BoxLayout.X_AXIS));
		frmBlackjack.getContentPane().setLayout(new FlowLayout());
	}
	
	private void createDialog(){
		menu = new JPanel();
		frmBlackjack.getContentPane().add(menu);
		menu.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtMinimumBet = new JTextField();
		txtMinimumBet.setText("Minimum Bet");
		txtMinimumBet.setToolTipText("");
		menu.add(txtMinimumBet);
		txtMinimumBet.setColumns(10);
		
		txtMaximumBet = new JTextField();
		txtMaximumBet.setToolTipText("");
		txtMaximumBet.setText("Maximum Bet");
		txtMaximumBet.setColumns(10);
		menu.add(txtMaximumBet);
		
		txtBalance = new JTextField();
		txtBalance.setToolTipText("");
		txtBalance.setText("Balance");
		txtBalance.setColumns(10);
		menu.add(txtBalance);
		
		txtShoe = new JTextField();
		txtShoe.setToolTipText("");
		txtShoe.setText("Shoe");
		txtShoe.setColumns(10);
		menu.add(txtShoe);
		
		txtShuffle = new JTextField();
		txtShuffle.setToolTipText("");
		txtShuffle.setText("Shuffle");
		txtShuffle.setColumns(10);
		menu.add(txtShuffle);
		
		int result = JOptionPane.showConfirmDialog(frmBlackjack, menu,"LALAAL",JOptionPane.OK_CANCEL_OPTION);
		
	}
	
	private void createGamePanel(PlayingAreaGUI pa){
		game = new JPanel();
		frmBlackjack.getContentPane().add(game);
		game.setLayout(new BoxLayout(game, BoxLayout.X_AXIS));
		game.setVisible(true);
		
		playingAreaPanel = new JPanel();
		game.add(playingAreaPanel);
		playingAreaPanel.setOpaque(false);
		playingAreaPanel.setLayout(new BoxLayout(playingAreaPanel, BoxLayout.Y_AXIS));
		
		dealerArea = new JPanel();
		dealerArea.setOpaque(false);
		playingAreaPanel.add(dealerArea);
		dealerArea.setLayout(new BoxLayout(dealerArea, BoxLayout.Y_AXIS));
		
		dealerCards = new JPanel();
		dealerCards.setOpaque(false);
		dealerCards.setMaximumSize(new Dimension(100, 100));
		dealerArea.add(dealerCards);
		pa.dealerJP = dealerCards;
		
		txtDealerpoints = new JTextField();
		txtDealerpoints.setMaximumSize(new Dimension(20, 20));
		txtDealerpoints.setText("dealerPoints");
		dealerArea.add(txtDealerpoints);
		txtDealerpoints.setColumns(10);
		
		JPanel playerArea = new JPanel();
		playerArea.setOpaque(false);
		playingAreaPanel.add(playerArea);
		playerArea.setLayout(new BoxLayout(playerArea, BoxLayout.Y_AXIS));
		
		playerCards = new JPanel();
		playerCards.setOpaque(false);
		playerCards.setMaximumSize(new Dimension(100, 100));
		playerCards.setLayout(new BoxLayout(playerCards, BoxLayout.Y_AXIS));
		playerArea.add(playerCards);
		pa.playerJP = playerCards;
		
		//DEBUG
		btnCard = new JButton("");
		btnCard.setOpaque(false);
		btnCard.setContentAreaFilled(false);
		btnCard.setIcon(new ImageIcon(Game.class.getResource("/project/assets/cards/2S.png")));
		playerCards.add(btnCard);
		
		
		txtPlayerPoints = new JTextField();
		txtPlayerPoints.setText("playerPoints");
		txtPlayerPoints.setMaximumSize(new Dimension(20, 20));
		playerArea.add(txtPlayerPoints);
		txtPlayerPoints.setColumns(10);
		
		commandArea = new JPanel();
		commandArea.setOpaque(false);
		playingAreaPanel.add(commandArea);
		commandArea.setLayout(new BoxLayout(commandArea, BoxLayout.X_AXIS));
		
		btnBet = new JButton("bet");
		btnBet.addActionListener(new CmdActionListener("b",pa));
		commandArea.add(btnBet);
		
		btnDeal = new JButton("deal");
		btnDeal.addActionListener(new CmdActionListener("d",pa));
		commandArea.add(btnDeal);
		
		btnHit = new JButton("hit");
		btnHit.addActionListener(new CmdActionListener("h",pa));
		commandArea.add(btnHit);
		
		btnStand = new JButton("stand");
		btnStand.addActionListener(new CmdActionListener("s",pa));
		commandArea.add(btnStand);
		
		btnInsurance = new JButton("insurance");
		btnInsurance.addActionListener(new CmdActionListener("i",pa));
		commandArea.add(btnInsurance);
		
		btnDouble = new JButton("double");
		btnDouble.addActionListener(new CmdActionListener("2",pa));
		commandArea.add(btnDouble);
		
		btnSplit = new JButton("split");
		btnSplit.addActionListener(new CmdActionListener("p",pa));
		commandArea.add(btnSplit);
		
		btnSurrender = new JButton("surrender");
		btnSurrender.addActionListener(new CmdActionListener("u",pa));
		commandArea.add(btnSurrender);
		
		btnQuit = new JButton("quit");
		btnQuit.addActionListener(new CmdActionListener("q",pa));
		commandArea.add(btnQuit);
		
		chipsArea = new JPanel();
		chipsArea.setBorder(null);
		game.add(chipsArea);
		chipsArea.setOpaque(false);
		chipsArea.setLayout(new BoxLayout(chipsArea, BoxLayout.X_AXIS));
		
		pot = new JTextField();
		chipsArea.add(pot);
		pot.setMaximumSize(new Dimension(20, 20));
		pot.setColumns(10);
		pot.setEditable(false);
		
		chips = new JPanel();
		chips.setBorder(null);
		chips.setOpaque(false);
		chipsArea.add(chips);
		chips.setLayout(new BoxLayout(chips, BoxLayout.Y_AXIS));
		
		
		btnChipBlack = new JButton("");
		btnChipBlack.addActionListener(new ChipActionListener(100,pa,pot));
		btnChipBlack.setBorderPainted(false);
		btnChipBlack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChipBlack.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnChipBlack.setBorderPainted(false);
			}
		});
		btnChipBlack.setOpaque(false);
		btnChipBlack.setContentAreaFilled(false);
		btnChipBlack.setIcon(new ImageIcon(Game.class.getResource("/project/assets/chips/blackchip.png")));
		chips.add(btnChipBlack);
		
		btnChipWhite = new JButton("");
		btnChipWhite.addActionListener(new ChipActionListener(1,pa,pot));
		btnChipWhite.setBorderPainted(false);
		btnChipWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChipWhite.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnChipWhite.setBorderPainted(false);
			}
		});
		btnChipWhite.setOpaque(false);
		btnChipWhite.setContentAreaFilled(false);
		btnChipWhite.setIcon(new ImageIcon(Game.class.getResource("/project/assets/chips/whitechip.png")));
		chips.add(btnChipWhite);
		
		btnChipGreen = new JButton("");
		btnChipGreen.addActionListener(new ChipActionListener(25,pa,pot));
		btnChipGreen.setBorderPainted(false);
		btnChipGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChipGreen.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnChipGreen.setBorderPainted(false);
			}
		});
		btnChipGreen.setOpaque(false);
		btnChipGreen.setContentAreaFilled(false);
		btnChipGreen.setIcon(new ImageIcon(Game.class.getResource("/project/assets/chips/greenchip.png")));
		chips.add(btnChipGreen);
		
		btnChipRed = new JButton("");
		btnChipRed.addActionListener(new ChipActionListener(5,pa,pot));
		btnChipRed.setBorderPainted(false);
		btnChipRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChipRed.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnChipRed.setBorderPainted(false);
			}
		});
		btnChipRed.setOpaque(false);
		btnChipRed.setContentAreaFilled(false);
		btnChipRed.setIcon(new ImageIcon(Game.class.getResource("/project/assets/chips/redchip.png")));
		chips.add(btnChipRed);
		
		btnPlay = new JButton("Play!");
		frmBlackjack.getContentPane().add(btnPlay);
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmBlackjack.getContentPane().add(game);
				game.setVisible(true);
				frmBlackjack.getContentPane().setLayout(new BoxLayout(frmBlackjack.getContentPane(),BoxLayout.X_AXIS));
				frmBlackjack.repaint();
				btnPlay.setVisible(false);
			}
		});
		buttonGroup.add(btnPlay);
		
		game.setVisible(false);
		
	}	

}
