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
	
	char command;
	int arg;
	
	/** Constructor for a Command object
	 * 
	 * @param string user input
	 */
	public Command(String string) {
		this.command = string.charAt(0);
		if(this.command=='b')
			this.arg = Integer.parseInt(string.substring(2));
	}
	
	/** Constructor for a Command object
	 * 
	 * @param string user input
	 */
	public Command(StringBuffer string) {
		this.command = string.charAt(0);
		if(this.command=='b')
			this.arg = Integer.parseInt(string.substring(2));
	}
	

}
