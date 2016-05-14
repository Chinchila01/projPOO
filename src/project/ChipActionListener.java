package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ChipActionListener implements ActionListener{
	PlayingAreaGUI pa;
	int buffer;
	JTextField text;
	
	ChipActionListener(int i, PlayingAreaGUI pa, JTextField t){
		buffer = i;
		this.pa = pa;
		text = t;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		pa.betBuffer += buffer;
		text.setText(String.valueOf(pa.betBuffer));
		
	}

}
