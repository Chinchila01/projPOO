package project;

import java.io.*;
import java.util.Scanner;

/**
 * Constructor for Playing Area Debug
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class PlayingAreaDebug extends PlayingArea {
	Scanner cmdFile;
	String cmds;
	public static int minimumBet;
	
	/**
	 * Constructor for Playing Area Debug
	 * @param minBet
	 * @param maxBet
	 * @param initialMoney
	 * @param shoeFile - contains all the cards to be apart of the Shoe
	 * @param inCmdFile - contains the commands to be played
	 */
	public PlayingAreaDebug(int minBet, int maxBet, float initialMoney, String shoeFile, String inCmdFile) {
		super(minBet, maxBet, initialMoney);
		try{
			this.shoe = new Shoe(shoeFile);  //create shoe from shoe file
		}catch(FileNotFoundException e){
			System.out.println("Shoe file not found: " + e.getMessage());
			System.exit(1);
		}catch(NotParseableException e){
			System.out.println("Shoe file badly formatted: " + e.getMessage());
			System.exit(1);
		}
		
		try {
			cmdFile = new Scanner(new File(inCmdFile)); //import cmd file	
		}catch(FileNotFoundException e){
			System.out.println("Command file not found: " + e.getMessage());
			System.exit(1);
		}
		
		previousBet = minBet;
		ad = new Advisor(minBet,maxBet,shoe.getNbDecks());
	}
	
	public String getCommand() throws NoMoreCmdsException{
		if(cmdFile.hasNext()) {
			String cmd = cmdFile.next();
			if(cmd.equals("b") && cmdFile.hasNextInt()){ //if is a bet and next input is a number, command is everything
				cmd += " " + cmdFile.next();
			}
			System.out.println("-cmd " + cmd);
			return cmd;
		} else throw new NoMoreCmdsException();
	}
	
	
	public void quit(){
		cmdFile.close();
		System.exit(0);
	}	

}
