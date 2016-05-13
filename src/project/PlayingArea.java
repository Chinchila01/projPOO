package project;

/**
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public abstract class PlayingArea {
	
	/**
	 * Common attributes to all game modes
	 */
	int minBet;
	int maxBet;
	int previousBet;
	float initialMoney;
	Shoe shoe;
	Statistics stat;
	static boolean dealDone;
	static boolean betDone;
	Advisor ad;
	Player player;
	Dealer dealer;
	
	
	public PlayingArea(int minBet, int maxBet, float initialMoney) {
		this.minBet = minBet;
		this.maxBet = maxBet;
		this.previousBet = minBet;
		this.initialMoney = initialMoney;
		this.stat = new Statistics(initialMoney);
		dealDone=false;
		betDone=false;
		player = new Player(initialMoney, minBet, maxBet);
		dealer = new Dealer(initialMoney, minBet, maxBet);
	}
	
	/**
	 * Takes in a command and executes the command accordingly
	 * 
	 * @param cmd
	 * @param player object
	 * @param dealer object
	 */
	public void executePlayerAction(String cmd) throws IllegalCmdException {
		
		int bet=0;
		Hand playerCurrHand = player.getCurrHand();
		String[] cmdHelper;
		
		if(cmd.charAt(0)=='b') {
			if(dealDone==true) throw new IllegalCmdException("b: illegal command");
			
			if(cmd.length()==1)		//betting without specifying value will default to last bet
				bet = this.previousBet;
			else if(cmd.length()>1) {
				cmdHelper = cmd.split(" ");
				bet = Integer.parseInt(cmdHelper[1]);
			}
			
			try{
				player.addPlayerMoney(-bet);
				playerCurrHand.addBet(bet);
			}catch(NotEnoughMoneyException e){
				printMessage("betting not possible: " + e.getMessage());
			}catch(IllegalCmdException e){
				printMessage("betting not possible: " + e.getMessage());
				try{
					player.addPlayerMoney(bet);
				}catch(NotEnoughMoneyException ex){
					printMessage("bet reversing not possible, please restart the game");
					printMessage(e.getMessage());
				}
			}
			printMessage("Player is betting "+bet);
			this.previousBet = playerCurrHand.curBet;
			betDone=true;
		}
			
		if(cmd.equals("$")) {	// prints current player balance
			printMessage("Current balance: " + player.getPlayerMoney());
		}
			
		if(cmd.equals("d")) {	// deal
			
			if(betDone==false) throw new IllegalCmdException("d: illegal command");
			
			if(dealDone==false) {
								
				// give cards to dealer
				ad.observeCard(dealer.hit(shoe),shoe.getDecksLeft());
				ad.observeCard(dealer.hit(shoe),shoe.getDecksLeft());			
				dealer.hand.getCards().listIterator(1).next().isTurnedUp = false;
				
				// give cards to player
				ad.observeCard(player.hit(shoe),shoe.getDecksLeft());
				ad.observeCard(player.hit(shoe),shoe.getDecksLeft());
			}
			
			printMessage(dealer);
			printMessage(player);
			
			dealDone=true;
			
		}
			
		if(cmd.equals("h")) {	// hit
			
			if(dealDone==false) throw new IllegalCmdException("h: illegal command");
			
			ad.observeCard(player.hit(shoe),shoe.getDecksLeft());
			printMessage("player hits");
			printMessage(player);
			if(playerCurrHand.busted) {
				printMessage("player busts" + ((player.currHand > 0) ? (" [" + (player.currHand+1) + "]"):""));
			}
			playerCurrHand.hitDone=true;
		}
			
		if(cmd.equals("s")) {	//stand
			
			if(dealDone==false) throw new IllegalCmdException("s: illegal command");
			
			//if(itPlayer.hasNext()) 
			//	playerCurrHand=itPlayer.next();//gets next hand if exists
			//else pa.validHands = false;
			
			player.stand();
			playerCurrHand.standDone=true;
			if(player.hand.size() == 1)
				printMessage("player stands");
			else {
				printMessage("player stands [" + (player.currHand+1) + "]" );
			}
		}
		
		if(cmd.equals("i")) {	// insurance
			
			if(!dealDone || playerCurrHand.hitDone || playerCurrHand.standDone) throw new IllegalCmdException("i: illegal command");
			
			try{
				player.addPlayerMoney(-playerCurrHand.curBet);
				player.insurance(dealer.hand);
			}catch(NotEnoughMoneyException e){
				printMessage("insurance not possible: " + e.getMessage());
				return;
			}catch(IllegalHandException e){
				printMessage("insurance not possible: " + e.getMessage());
				try{
					player.addPlayerMoney(playerCurrHand.curBet);
					return;
				}catch(NotEnoughMoneyException e1){
					printMessage(e1.getMessage());
					return;
				}
			}
		}
			
		if(cmd.equals("u")) {	// surrender

			if(!dealDone || playerCurrHand.hitDone || playerCurrHand.standDone) throw new IllegalCmdException("u: illegal command");
			
			float money = 0;
			try {
				money = player.surrender(dealer.hand);
				player.addPlayerMoney(money);
				return;
			} catch(IllegalHandException e){
				printMessage("surrender not possible: " + e.getMessage());
				return;
			} catch(NotEnoughMoneyException e){
				printMessage("surrender not possible: " + e.getMessage());
				try{
					player.addPlayerMoney(-money);
					return;
				}catch(NotEnoughMoneyException ex){
					printMessage(e.getMessage());
					return;
				}
			}
			//if(itPlayer.hasNext()) 
			//	playerCurrHand=itPlayer.next();//gets next hand if exists
			//else pa.validHands = false;
		}
			
		if(cmd.equals("p")) {	// splitting

			if(!dealDone || playerCurrHand.hitDone || playerCurrHand.standDone) throw new IllegalCmdException("p: illegal command");
			
			try{
				player.split(playerCurrHand, shoe);
				printMessage("Player is spliting...");
				printMessage("Playing "  + player.hand.size()/2 + "st hand...");
				printMessage("Player's hand [" + (player.currHand+1) + "] " + playerCurrHand);
				return;
			} catch(IllegalHandException e){
				printMessage(e.getMessage());
				printMessage("split not available");
				return;
			} catch(NotEnoughMoneyException e){
				printMessage("split not possible: " + e.getMessage());
			}
			
		}
			
		if(cmd.equals("2")) {	// double

			if(!dealDone || playerCurrHand.hitDone || playerCurrHand.standDone) throw new IllegalCmdException("2: illegal command");
			
			try {
				player.doubleBet();
				printMessage("bet doubled");
				return;
			} catch (IllegalHandException e) {
				printMessage("doubling not possible: " + e.getMessage());
				return;
			} catch (NotEnoughMoneyException e) {
				printMessage("doubling not possible: " + e.getMessage());
				return;
			}
		}
			//TODO: DONT FORGET ABOUT 10 score
		if(cmd.equals("ad")) {	// advice
			if(!dealDone) System.out.println("bet\t " + ad.advise(previousBet));
			else {
				String[] strategies = ad.advise(dealDone, player, dealer.hand.cards.iterator().next());
				
				if(!strategies[0].equals("")) printMessage("basic\t" + ad.basicInterpret(dealDone, player, strategies[0]));
				if(!strategies[1].equals("")) printMessage("hilo\t" + ((strategies[1].length() < 2) ? ad.hlInterpret(dealDone,player,strategies[1]) : ad.basicInterpret(dealDone, player, strategies[1])));
			}
		}
			
		if(cmd.equals("st")) {	// statistics
			try{
				stat.presentStatistics();
			}catch(NoPlayedRoundsException e){
				printMessage("statistics unavailable: " + e.getMessage());
			}
		}
		
		if(cmd.equals("q")) {	// player inputs 'q' to quit the game
			this.quit();
		}
				
	}
	
	/**
	 * checks win/lose conditions and pays the player if applicable
	 * @param player object
	 * @param dealer object
	 */
	public void payOut(){
		
		for(Hand eachHand : player.hand){
			 
			//TODO: escolher valor do as
			if(eachHand.surrender){
				printMessage("player's current balance is " + player.getPlayerMoney());
				ad.updateStdStrat(true);
				stat.addLoss();
			}
			// O jogador tem um blackjack
			else if(eachHand.hasBlackjack){
				stat.addPlayerBJ();
				if(dealer.hand.hasBlackjack) { // dealer tambem tem blackjack
					if(eachHand.insured) {
						try{
							player.addPlayerMoney(eachHand.curBet); //the player gets twice the current money
						}catch(NotEnoughMoneyException e){
							printMessage(e.getMessage());
						}
					}
					try{
						player.addPlayerMoney(eachHand.curBet);
					}catch(NotEnoughMoneyException e){
						printMessage(e.getMessage());
					}
					
					printMessage("blackjack!!");
					printMessage("player pushes and his current balance is " + player.getPlayerMoney());
					// Update statistics
					stat.addPush();
					stat.addDealerBJ();
					//update stdstrat
				}
				else{ // dealer nao tem blackjack
					try{
						player.addPlayerMoney((float)2.5*eachHand.curBet);
					} catch(NotEnoughMoneyException e){
						printMessage(e.getMessage());
					}
					printMessage("player wins with a blackjack and his current balance is " + player.getPlayerMoney());
					stat.addWin();
					ad.updateStdStrat(false);
				}
			}
			else if(eachHand.busted){
				printMessage("player loses and his current balance is " + player.getPlayerMoney());
				stat.addLoss();
				ad.updateStdStrat(true);
			}
			else if(dealer.hand.busted) {	// dealer Bust
				try{
					player.addPlayerMoney(2*eachHand.curBet);
				}catch(NotEnoughMoneyException e){
					printMessage(e.getMessage());
				}
				printMessage("player wins and his current balance is " + player.getPlayerMoney());
				stat.addWin();
				ad.updateStdStrat(false);
			}
			else if(dealer.hand.hasBlackjack && eachHand.insured){
				try{
					player.addPlayerMoney(eachHand.curBet);
				}catch(NotEnoughMoneyException e){
					printMessage(e.getMessage());
				}
			}
			else if(dealer.hand.getScore() > eachHand.getScore()) { // player bust ou dealer tem mais pontos
				printMessage("player loses and his current balance is " + player.getPlayerMoney());
				stat.addLoss();
				ad.updateStdStrat(true);
			}
			else if(eachHand.getScore() == dealer.hand.getScore()){
				try{
					player.addPlayerMoney(eachHand.curBet);
				}catch(NotEnoughMoneyException e){
					printMessage(e.getMessage());
				}
				printMessage("player pushes and his current balance is " + player.getPlayerMoney());
				stat.addPush();
			}
			else { //player tem mais pontos
				try{
					player.addPlayerMoney(2*eachHand.curBet);
				}catch(NotEnoughMoneyException e){
					printMessage(e.getMessage());
				}
				printMessage("player wins and his current balance is " + player.getPlayerMoney());
				stat.addWin();
				ad.updateStdStrat(false);
			}
		}
		
		stat.setBalance(player.getPlayerMoney());
	}
	
	/**
	 * plays the dealer's turn. The dealer stands on all 17s and hits otherwise
	 * @param dealer
	 */
	public void dealerTurn(){
		
		Hand dealerCurrHand = dealer.hand;
		dealerCurrHand.getCards().listIterator(1).next().isTurnedUp = true; //turn hole
		
		printMessage("dealer's hand " + dealer.getHand() + " (" + dealerCurrHand.getScore() + ")");
		
		while(dealerCurrHand.getScore() < 17) { //dealer stands on all 17s
			ad.observeCard(dealer.hit(shoe),shoe.getDecksLeft());
			printMessage("dealer hits");
			printMessage("dealer's hand " + dealer.getHand() + " (" + dealerCurrHand.getScore() + ")");
		}
		
		if(dealerCurrHand.hasBlackjack) {
			printMessage("blackjack!!");
		}
		
		printMessage("dealer stands");
	}
	
	/**
	 * Prepares for next round, by putting the hands in the shoe
	 * 
	 * @param player
	 * @param dealer
	 */
	public void prepareNextRound(){
		
		player.resetHands(shoe);
		dealer.resetHands(shoe);
		
		dealDone=false;
		betDone=false;		
		
		printMessage("Starting a new round");
	}
	
	/**
	 * Gets the next command to be played
	 * @return next command to be played
	 */
	public abstract String getCommand() throws NoMoreCmdsException;
	
	/**
	 * Detects if there is another command waiting to be played
	 * @return true if there is another command, false otherwise
	 */
	public boolean hasNextCommand(){
		return true;
	}
	
	/**
	 * quits the game
	 */
	public abstract void quit();
	
	/**
	 * Prints a message
	 */
	public void printMessage(String s){
		System.out.println(s);
	}
	
	/**
	 * Prints an object's textual description
	 */
	public void printMessage(Object o){
		printMessage(o.toString());
	}

}
