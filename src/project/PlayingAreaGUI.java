package project;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayingAreaGUI extends PlayingAreaInteractive {
	
	JPanel dealerJP, playerJP;
	JFrame frame;
	String cmdBuffer;
	int betBuffer;
	boolean validCmd;
	boolean updatePCards;
	
	public PlayingAreaGUI(int minBet, int maxBet, float initialMoney, int nbDecks, int shufflePercent, JPanel dJP, JPanel pJP, JFrame frame) {
		
		super(minBet, maxBet, initialMoney, nbDecks, shufflePercent);
		this.dealerJP = dJP;
		this.playerJP = pJP;
		this.frame = frame;
		this.cmdBuffer = "";
		betBuffer = 0;
		validCmd = false;
		updatePCards = false;
	}
	
	
	private JButton addCardToPanel(Card c, JPanel jp) {
		
		JButton cardButton = new JButton("");
		cardButton.setOpaque(false);
		cardButton.setPreferredSize(new Dimension(33, 20));
		cardButton.setContentAreaFilled(false);
		String str = "/project/assets/cards/" + c.getSymbol() + c.getSuit() + ".png";
		System.out.println(str);
		cardButton.setIcon(new ImageIcon(PlayingAreaGUI.class.getResource("/project/assets/cards/2C.png")));
		jp.add(cardButton);
		cardButton.setVisible(true);
		
		return cardButton;
		
	}
	
	private void removeCardsFromPanel(JPanel jp) {
		jp.removeAll();
	}
	
	@Override
	public String getCommand() {
		
		removeCardsFromPanel(dealerJP);
		removeCardsFromPanel(playerJP);
		
		//for(Card c : dealer.hand.getCards()) {
		//	addCardToPanel(c, dealerJP);
		//}
		
		if(updatePCards){
			for(Card c : player.getCurrHand().getCards()) {
			addCardToPanel(c, playerJP);
			}
			updatePCards = false;
			frame.repaint();
		}
		
		if(validCmd){
			System.out.println(cmdBuffer);
			String str = cmdBuffer;
			int bet = betBuffer;
			cmdBuffer = "";
			betBuffer = 0;
			validCmd = false;
			if(str.equals("b")) {
				return "b " + bet;
			}
			else{ 
				return new String(str);
			}
		}
		
		return "";
	}
	
	public void quit(){
		frame.dispose();
		System.exit(0);
	}

}
