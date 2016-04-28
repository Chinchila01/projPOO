package project;


/** Command Class
 * 
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 * @version 1.0
 */
public class Command {
	
	StringBuffer command="";
	int arg=0;
	
	/** Constructor for a Command object
	 * 
	 * Stores user input in attribute command. If user input is
	 * 'b' then checks for a second argument. If it exists, it is 
	 * stored in attribute arg. 
	 * 
	 * @param string user input
	 */
	public Command(String string) {
		
		String[] args = string.split(" ");
		
		this.command.append(args[0]);
		if(this.command.charAt(0)=='b' && args.length==2) {
			this.command.append("1");
			this.arg = Integer.parseInt(args[1]);
		}
		else if(this.command.charAt(0)=='b' && args.length==1) {
			this.command.append("0");
		}
	}
	
	

}
