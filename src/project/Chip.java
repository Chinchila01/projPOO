package project;

/** Chip Class
 * Has a color and a respective value.
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class Chip {
	
	char color;
	int value;
	
	/** Constructor for a Chip object
	 * 
	 * @param value is the value for the chip. A color is automatically
	 * associated with the value. An exception is thrown if value is not valid.
	 * Valid values are 1, 5, 25 and 100.
	 */
	public Chip(int value) {
		switch(value) {
		case 100:
			this.color = 'b';
			this.value = 100;
			break;
		case 25:
			this.color = 'g';
			this.value = 25;
			break;
		case 5:
			this.color = 'r';
			this.value = 5;
			break;
		case 1:
			this.color = 'w';
			this.value = 1;
			break;
		default:
			//TODO: throw exception
		}
	}
	
	/** Constructor for a Chip object
	 * 
	 * @param color is the color for the chip. A value is automatically
	 * associated with the color. An exception is thrown if color is not valid.
	 * Valid colors are black, green, red and white ('b', 'g', 'r', 'w').
	 */
	public Chip(char color) {
		switch(color) {
		case 'b':
			this.color = 'b';
			this.value = 100;
			break;
		case 'g':
			this.color = 'g';
			this.value = 25;
			break;
		case 'r':
			this.color = 'r';
			this.value = 5;
			break;
		case 'w':
			this.color = 'w';
			this.value = 1;
			break;
		default:
			//TODO: throw exception
		}
	}

}
