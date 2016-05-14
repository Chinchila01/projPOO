package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CmdActionListener implements ActionListener {
	String buffer;
	PlayingAreaGUI pa;
	
	public CmdActionListener(String q, PlayingAreaGUI pa){
		buffer = q;
		this.pa = pa;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		pa.cmdBuffer = buffer;
		pa.validCmd = true;
		if(buffer.equals("h") || buffer.equals("d")) pa.updatePCards=true;
	}

		
}
