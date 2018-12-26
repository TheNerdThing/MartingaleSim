import java.util.Arrays;

/**
 * 
 */

/**
 * @author A692456
 *
 */
public class MartingaleTester {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final int WIN_ON = 49; 								// player will win if the number rolled is greater than this
		final int MAX_ROLL = 100; 							// math.random will select this for a max. 
		final int STARTING_BANKROLL = 10000; 				// the amount of money the player will risk
		final int STARTING_BET = 5;							// the initial bet
		final int TRIALS_TO_RUN = 10000;					// number of sims to run. one loop is one sim 
		final int TABLE_MAX = 1000;							// the max bet a player can bet. if a player exceeds the bet limit he will bet the limit. 
		final boolean IS_TABLE_MAX = TABLE_MAX != 0; 		// If the max bet is 0 there will be no max bet. 
		final int END_GOAL = (int)(STARTING_BANKROLL * 2); 	// a sim will run until a player either reaches 0 or his goal. 
		final boolean MARTINGALE = true;					// true = after we lose a bet, the player's next wager will be 2x his current wager. 
															// false =we do not change our bet size.
		
		int bankRoll = STARTING_BANKROLL; 
		int currentBet = STARTING_BET;
		int gamesRan = 0;									// this will keep track of how many games were played before the player went bust 
		int numberOfSims = 0; 
		int[] data = new int[TRIALS_TO_RUN];				// we will keep track of gamesRan for each sim
		int playerMetGoal = 0;								// tracks how many times the player reached its goal. 
		int playerwins =0;									// tracks how many games the player has won
		
		while(numberOfSims < TRIALS_TO_RUN) {
			// start of a sim
			do {
				//wager bet
				bankRoll -= currentBet;
				// roll dice
				int roll = (int)((Math.random() * MAX_ROLL)) -1;
				gamesRan ++;
				//did the player win? 
				if(roll >= WIN_ON) {
					// add 2x his current bet and set the current bet to starting bet
					bankRoll += currentBet *2;
					currentBet = STARTING_BET;
					playerwins ++;
					if(IS_TABLE_MAX && currentBet > TABLE_MAX) {
						currentBet = TABLE_MAX;
					}
				}else {
					// on loss, double the bet if we are running using martingale
					if(MARTINGALE)
						currentBet = currentBet *2;
				}
			// repeat the game while there is still money to gamble, the player can still match his bet, and the player has not met his goal. 
			}while( bankRoll > 0 && currentBet <= bankRoll && bankRoll <= END_GOAL);
			if(bankRoll > END_GOAL) {
				playerMetGoal++;
			}
			// once the player has busted or met his goal, 
			data[numberOfSims] = gamesRan;
			gamesRan = 0; 
			numberOfSims ++;
			//restart the game
			currentBet = STARTING_BET;
			bankRoll = STARTING_BANKROLL;
		}
		
		Arrays.sort(data);
		int totalGames =0;		// how many games ran for every sim. 
		
		// all data is gathered to determine if the player was lucky or unlucky during each run.
		for(int i = 0; i<data.length; i++) {
			int use = data[i];
			totalGames += use;
		}

		System.out.println(" the player met his goal : " + playerMetGoal + " player retire rate is : " + ((double) playerMetGoal /TRIALS_TO_RUN));
		System.out.println("the player's winrate is:  " + (double) playerwins/totalGames + " ( " + playerwins + "  wins total)" + "( " + totalGames + " games total)") ;
	}
}
