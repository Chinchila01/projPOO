package project;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayingAreaGUI extends PlayingAreaInteractive {
	
	JPanel dealerJP, playerJP;
	String cmdBuffer;
	int betBuffer;

	public PlayingAreaGUI() {
		
		super(minBet, maxBet, initialMoney, nbDecks, shufflePercent);
		this.dealerJP = dJP;
		this.playerJP = pJP;
		this.cmdBuffer = "";
		betBuffer = 0;
		
	}
	
	
	private JButton addCardToPanel(Card c, JPanel jp) {
		
		JButton cardButton = new JButton("");
		cardButton.setOpaque(false);
		cardButton.setPreferredSize(new Dimension(33, 20));
		cardButton.setContentAreaFilled(false);
		cardButton.setIcon(new ImageIcon(Game.class.getResource("/project/assets/chips/" + c.getSymbol() + c.getSuit() + ".png")));
		jp.add(cardButton);
		
		return cardButton;
		
	}
	
	private void removeCardsFromPanel(JPanel jp) {
		jp.removeAll();
	}
	
	@Override
	public String getCommand() {
		
		removeCardsFromPanel(dealerJP);
		removeCardsFromPanel(playerJP);
		
		for(Card c : player.getCurrHand().getCards()) {
			addCardToPanel(c, playerJP);
		}
		for(Card c : dealer.hand.getCards()) {
			addCardToPanel(c, dealerJP);
		}
		
		String str = cmdBuffer;
		int bet = betBuffer;
		cmdBuffer = "";
		betBuffer = 0;
		
		if(str.equals("b")) {
			return "b " + bet;
		}
		else{ 
			return new String(cmdBuffer);
		}
	}

}
