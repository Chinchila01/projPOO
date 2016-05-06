package project;

import java.io.*;
import java.util.Scanner;

/**
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class PlayingAreaDebug extends PlayingArea {
	
	Scanner cmdFile;
	String cmds;
	
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
		
		try{
			this.shoe = new Shoe(args[4]);  //create shoe from shoe file
		}catch(FileNotFoundException e){
			System.out.println("Shoe file not found: " + e.getMessage());
			System.exit(1);
		}
		
		//this.shoeFile = args[4];
		try {
			cmdFile = new Scanner(new File(args[5])); //import cmd file	
		}catch(FileNotFoundException e){
			System.out.println("Command file not found: " + e.getMessage());
			System.exit(1);
		}
		
		previousBet = minBet;
	}
	
	//TODO: fix,temporary
	public String getCommand(){
		if(cmdFile.hasNext()) {
			String cmd = cmdFile.next();
			System.out.println("-cmd " + cmd);
			return cmd;
		} else return ""; //TODO: FIX
	}
	
	//TODO: Fix, temporary
	public boolean hasNextCommand(){
		return false;
	}
	
	//TODO: fix, temporary
	public void quit(){
		System.exit(0);
	}
	
	

}
