//ClientGUI for Remote Controller
//Made by Francisco Salgado
//github.com/d3rezz

package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientGUI {
	//GUI components
	public JFrame mainFrame = null;
	public JButton upButton  = null;
	public JButton downButton = null;
	public JButton leftButton = null;
	public JButton rightButton = null;
	public JTextArea textArea = null;

	//For solving repeated pressed events
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	private boolean[] keyPressed = new boolean[4];

	public void upButtonPressed() {
		System.out.print("Up button pressed\n");
	}


	public void upButtonReleased() {
		System.out.print("Up button released\n");
	}


	public void rightButtonPressed() {
		System.out.print("right button pressed\n");
	}


	public void rightButtonReleased() {
		System.out.print("right button released\n");
	}

	public void leftButtonPressed() {
		System.out.print("left button pressed\n");
	}


	public void leftButtonReleased() {
		System.out.print("left button released\n");
	}


	public void downButtonPressed() {
		System.out.print("down button pressed\n");
	}


	public void downButtonReleased() {
		System.out.print("down button released\n");
	}


	public void cleanUp() {
		System.out.print("cleaning up...\n");
	}

	private void setupButtons() {
		//Up Button
		upButton = new JButton("Forward");
		upButton.setFocusable(false);
		upButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Thread queryThread = new Thread() {
					public void run() {
						upButtonPressed();
					}
				};
				queryThread.start();
			}
			public void mouseReleased(MouseEvent e) {
				Thread queryThread = new Thread() {
					public void run() {
						upButtonReleased();
					}
				};
				queryThread.start();
			}
		});

    //Right Button
    rightButton = new JButton("Right");
		rightButton.setFocusable(false);
		rightButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Thread queryThread = new Thread() {
					public void run() {
						rightButtonPressed();
					}
				};
				queryThread.start();
			}
			public void mouseReleased(MouseEvent e) {
				Thread queryThread = new Thread() {
					public void run() {
						rightButtonReleased();
					}
				};
				queryThread.start();
			}
		});

		//Left Button
		leftButton = new JButton("Left");
		leftButton.setFocusable(false);
		leftButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Thread queryThread = new Thread() {
					public void run() {
						leftButtonPressed();
					}
				};
				queryThread.start();
			}
			public void mouseReleased(MouseEvent e) {
				Thread queryThread = new Thread() {
					public void run() {
						leftButtonReleased();
					}
				};
				queryThread.start();
			}
		});

		//Down Button
		downButton = new JButton("Down");
		downButton.setFocusable(false);
		downButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Thread queryThread = new Thread() {
					public void run() {
						downButtonPressed();
					}
				};
				queryThread.start();
			}
			public void mouseReleased(MouseEvent e) {
				Thread queryThread = new Thread() {
					public void run() {
						downButtonReleased();
					}
				};
				queryThread.start();
			}
		});
	}


	private void setupKeyboard(JPanel pane) {
		pane.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				final int keyCode = e.getKeyCode();
				Thread queryThread = new Thread() {
					public void run() {
						switch (keyCode) {
							case KeyEvent.VK_UP: if (keyPressed[UP] == false) {
																			keyPressed[UP] = true;
																			upButtonPressed();
																	}
            											break;
        			case KeyEvent.VK_DOWN: if (keyPressed[DOWN] == false) {
																			keyPressed[DOWN] = true;
																			downButtonPressed();
																	}
            											break;
        			case KeyEvent.VK_LEFT: if (keyPressed[LEFT] == false) {
																			keyPressed[LEFT] = true;
																			leftButtonPressed();
																	}
            											break;
	        		case KeyEvent.VK_RIGHT: if (keyPressed[RIGHT] == false) {
																			keyPressed[RIGHT] = true;
																			rightButtonPressed();
																	}
																	break;
						}
					}
				};
				queryThread.start();
			}


			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				final int keyCode = e.getKeyCode();
				Thread queryThread = new Thread() {
					public void run() {
						switch (keyCode) {
							case KeyEvent.VK_UP: upButtonReleased();
																		keyPressed[UP] = false;
            											break;
        			case KeyEvent.VK_DOWN: downButtonReleased();
																		keyPressed[DOWN] = false;
            											break;
        			case KeyEvent.VK_LEFT: leftButtonReleased();
																		keyPressed[LEFT] = false;
            											break;
	        		case KeyEvent.VK_RIGHT: rightButtonReleased();
																		keyPressed[RIGHT] = false;
																	break;
						}
					}
				};
				queryThread.start();
			}
		});
	}


	private void createAndShowGUI() {
		//Used to prevent repeated keys
		new RepeatingReleasedEventsFixer().install();

		//set up buttons
		setupButtons();

		//Setup controller buttons layout
		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.setFocusable(true);
    mainPane.requestFocusInWindow();
		mainPane.add(upButton, BorderLayout.NORTH);
		mainPane.add(rightButton, BorderLayout.EAST);
		mainPane.add(leftButton, BorderLayout.WEST);
		mainPane.add(downButton, BorderLayout.SOUTH);


		//Set up Keyboard events
		for (int i=0; i<4; i++) {
			keyPressed[i] = false;
		}
		setupKeyboard(mainPane);

		//Set up main frame
		mainFrame = new JFrame("Remote Controller");
		//when user presses X in window
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				cleanUp();
				System.exit(0);
			}
		});

		mainFrame.setContentPane(mainPane);
		mainFrame.setSize(mainFrame.getPreferredSize());
		mainFrame.setLocation(200, 200);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public ClientGUI() {
		//Set up GUI and events
		SwingUtilities.invokeLater(new Runnable() {
   			public void run() {
   				createAndShowGUI();
   			}
   		});
	}
}
