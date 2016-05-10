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
	
	
	public PlayingArea(int minBet, int maxBet, float initialMoney) {
		this.minBet = minBet;
		this.maxBet = maxBet;
		this.previousBet = minBet;
		this.initialMoney = initialMoney;
		this.stat = new Statistics(initialMoney);
		dealDone=false;
		betDone=false;
	}
	
	/**
	 * Takes in a command and executes the command accordingly
	 * 
	 * @param cmd
	 * @param player object
	 * @param dealer object
	 */
	public void executePlayerAction(String cmd, Player player, Dealer dealer) throws IllegalCmdException {
		
		int bet=0;
		Hand playerCurrHand = player.getCurrHand();
		String[] cmdHelper;
		
		if(cmd.charAt(0)=='b') {
			//TODO: assim so vai poder fazer bet na primeira mao, mudar isto
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
				System.out.println("betting not possible: " + e.getMessage());
			}catch(IllegalCmdException e){
				System.out.println("betting not possible: " + e.getMessage());
				try{
					player.addPlayerMoney(bet);
				}catch(NotEnoughMoneyException ex){
					System.out.println("bet reversing not possible, please restart the game");
					System.out.println(e.getMessage());
				}
			}
			this.previousBet = bet;
			betDone=true;
		}
			
		if(cmd.equals("$")) {	// prints current player balance
			System.out.println("Current balance: " + player.getPlayerMoney());
		}
			
		if(cmd.equals("d")) {	// deal
			
			if(betDone==false) throw new IllegalCmdException("d: illegal command");
			
			if(dealDone==false) {
								
				// give cards to dealer
				dealer.hit(shoe);
				dealer.hit(shoe);			
				dealer.hand.getCards().listIterator(1).next().isTurnedUp = false;
				
				// give cards to player
				player.hit(shoe);
				player.hit(shoe);
			}
			
			System.out.println("dealer's hand " + dealer.getHands());
			System.out.println("player's hand " + player.getHands());
			
			dealDone=true;
			
		}
			
		if(cmd.equals("h")) {	// hit
			
			if(dealDone==false) throw new IllegalCmdException("h: illegal command");
			
			player.hit(shoe);
			System.out.println("player hits");
			System.out.println("player's hand" + player.getHands());
			if(playerCurrHand.busted) {
				System.out.println("player busts");
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
			System.out.println("player stands");
			
		}
		
		if(cmd.equals("i")) {	// insurance
			
			//TODO: se fazer insurance antes do deal da erro porque o dealer ainda nao tem cartas
			if(!dealDone || playerCurrHand.hitDone || playerCurrHand.standDone) throw new IllegalCmdException("i: illegal command");
			
			try{
				player.addPlayerMoney(-playerCurrHand.curBet);
				player.insurance(dealer.hand);
			}catch(NotEnoughMoneyException e){
				System.out.println("insurance not possible: " + e.getMessage());
				return;
			}catch(IllegalHandException e){
				System.out.println("insurance not possible: " + e.getMessage());
				try{
					player.addPlayerMoney(playerCurrHand.curBet);
					return;
				}catch(NotEnoughMoneyException e1){
					System.out.println(e1.getMessage());
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
				System.out.println("surrender not possible: " + e.getMessage());
				return;
			} catch(NotEnoughMoneyException e){
				System.out.println("surrender not possible: " + e.getMessage());
				try{
					player.addPlayerMoney(-money);
					return;
				}catch(NotEnoughMoneyException ex){
					System.out.println(e.getMessage());
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
				return;
			} catch(IllegalHandException e){
				System.out.println(e.getMessage());
				System.out.println("split not available");
				return;
			}
		}
			
		if(cmd.equals("2")) {	// double

			if(!dealDone || playerCurrHand.hitDone || playerCurrHand.standDone) throw new IllegalCmdException("2: illegal command");
			
			try {
				player.doubleBet();
				System.out.println("bet doubled");
				return;
			} catch (IllegalHandException e) {
				System.out.println("doubling not possible: " + e.getMessage());
				return;
			} catch (NotEnoughMoneyException e) {
				System.out.println("doubling not possible: " + e.getMessage());
				return;
			}
		}
			
		if(cmd.equals("ad")) {	// advice
			//TODO
		}
			
		if(cmd.equals("st")) {	// statistics
			try{
				stat.presentStatistics();
			}catch(NoPlayedRoundsException e){
				System.out.println("statistics unavailable: " + e.getMessage());
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
	public void payOut(Player player, Dealer dealer){
		
		for(Hand eachHand : player.hand){
			
			//TODO: escolher valor do as
			//TODO: pushes
			if(eachHand.surrender){
				System.out.println("player's current balance is " + player.getPlayerMoney());
			}
			// O jogador tem um blackjack
			else if(eachHand.hasBlackjack){
				stat.addPlayerBJ();
				if(dealer.hand.hasBlackjack) { // dealer tambem tem blackjack
					if(eachHand.insured) {
						try{
							player.addPlayerMoney(eachHand.curBet); //the player gets twice the current money
						}catch(NotEnoughMoneyException e){
							System.out.println(e.getMessage());
						}
					}
					try{
						player.addPlayerMoney(eachHand.curBet);
					}catch(NotEnoughMoneyException e){
						System.out.println(e.getMessage());
					}
					
					System.out.println("blackjack!!");
					System.out.println("player pushes and his current balance is " + player.getPlayerMoney());
					// Update statistics
					stat.addPush();
					stat.addDealerBJ();
				}
				else{ // dealer nao tem blackjack
					try{
						player.addPlayerMoney((float)2.5*eachHand.curBet);
					} catch(NotEnoughMoneyException e){
						System.out.println(e.getMessage());
					}
					System.out.println("player wins with a blackjack and his current balance is " + player.getPlayerMoney());
					stat.addWin();
				}
			}
			else if(eachHand.busted){
				System.out.println("player loses and his current balance is " + player.getPlayerMoney());
				stat.addLoss();
			}
			else if(dealer.hand.busted) {	// dealer Bust
				try{
					player.addPlayerMoney(2*eachHand.curBet);
				}catch(NotEnoughMoneyException e){
					System.out.println(e.getMessage());
				}
				System.out.println("player wins and his current balance is " + player.getPlayerMoney());
				stat.addWin();
			}
			else if(dealer.hand.hasBlackjack && eachHand.insured){
				try{
					player.addPlayerMoney(eachHand.curBet);
				}catch(NotEnoughMoneyException e){
					System.out.println(e.getMessage());
				}
			}
			else if(dealer.hand.getScore() > eachHand.getScore()) { // player bust ou dealer tem mais pontos
				System.out.println("player loses and his current balance is " + player.getPlayerMoney());
				stat.addLoss();
			}
			else if(eachHand.getScore() == dealer.hand.getScore()){
				try{
					player.addPlayerMoney(eachHand.curBet);
				}catch(NotEnoughMoneyException e){
					System.out.println(e.getMessage());
				}
				System.out.println("player pushes and his current balance is " + player.getPlayerMoney());
				stat.addPush();
			}
			else { //player tem mais pontos
				try{
					player.addPlayerMoney(2*eachHand.curBet);
				}catch(NotEnoughMoneyException e){
					System.out.println(e.getMessage());
				}
				System.out.println("player wins and his current balance is " + player.getPlayerMoney());
				stat.addWin();
			}
		}
	}
	
	/**
	 * plays the dealer's turn. The dealer stands on all 17s and hits otherwise
	 * @param dealer
	 */
	public void dealerTurn(Dealer dealer){
		
		Hand dealerCurrHand = dealer.hand;
		dealerCurrHand.getCards().listIterator(1).next().isTurnedUp = true; //turn hole
		
		System.out.println("dealer's hand " + dealer.getHands() + " (" + dealerCurrHand.getScore() + ")");
		
		while(dealerCurrHand.getScore() < 17) { //dealer stands on all 17s
			dealer.hit(shoe);
			System.out.println("dealer hits");
			System.out.println("dealer's hand " + dealer.getHands() + " (" + dealerCurrHand.getScore() + ")");
		}
		
		if(dealerCurrHand.hasBlackjack) {
			System.out.println("blackjack!!");
		}
		
		System.out.println("dealer stands");
	}
	
	/**
	 * Prepares for next round, by putting the hands in the shoe
	 * 
	 * @param player
	 * @param dealer
	 */
	public void prepareNextRound(Player player, Dealer dealer){
		
		player.resetHands(shoe);
		dealer.resetHands(shoe);
		
		dealDone=false;
		betDone=false;

		System.out.println("Starting a new round");
	}
	
	/**
	 * Gets the next command to be played
	 * @return next command to be played
	 */
	public abstract String getCommand();
	
	/**
	 * Detects if there is another command waiting to be played
	 * @return true if there is another command, false otherwise
	 */
	public abstract boolean hasNextCommand();
	
	/**
	 * quits the game
	 */
	public abstract void quit();

}
