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
		final int WIN_ON = 40; // player will win if the number rolled is greater than this
		final int MAX_ROLL = 100; // math.random will select this for a max. 
		final int STARTING_BANKROLL = 10000;
		final int STARTING_BET = 5;
		final int TRIALS_TO_RUN = 10000;
		final int TABLE_MAX = 1000;
		final int END_GOAL = (int)(STARTING_BANKROLL * 2);
		
		int bankRoll = STARTING_BANKROLL; 
		int currentBet = STARTING_BET;
		int count = 0;
		int bustoCount = 0; 
		int[] data = new int[TRIALS_TO_RUN];
		int retierCount = 0;
		while(bustoCount < TRIALS_TO_RUN) {
			do {
				//wager bet
				bankRoll -= currentBet;
				// roll dice
				int roll = (int)((Math.random() * MAX_ROLL)) -1;
				count ++;
				//did the player win? 
				if(roll > WIN_ON) {
					// add 2x his current bet and set the current bet to starting bet
					bankRoll += currentBet *2;
					currentBet = STARTING_BET;
					if(currentBet > TABLE_MAX) {
						currentBet =TABLE_MAX;
					}
//					System.out.print("player has won the wager! ");
				}else {
					// on loss, double the bet
					currentBet = currentBet *2;
//					System.out.print("player has lost the wager! ");
				}
				// print the results
//				System.out.println(" player has a BR of: " + bankRoll + " after " + count + " trials " + " the next bet will be " + currentBet);
			}while( bankRoll > 0 && currentBet <= bankRoll && bankRoll <= END_GOAL);
			if(bankRoll > END_GOAL) {
				retierCount++;
			}
//			System.out.println("the player has busted after " + count + " trials");
			data[bustoCount] = count;
			count = 0; 
			bustoCount ++;
			currentBet = STARTING_BET;
			bankRoll = STARTING_BANKROLL;
		}
		
		Arrays.sort(data);
		int over1k = 0; 
		int over500 = 0; 
		int over100 = 0; 
		int over50 = 0;
		int under50 = 0;
		
		for(int i = 0; i<data.length; i++) {
			int use = data[i];
			System.out.println(use);
			if(use <= 50 ) {
				under50++;
			}
			else if( use <= 100) {
				over50 ++;
			}else if(use <=500) {
				over100 ++; 
			}else if(use <=1000) {
				over500 ++;
			}else {
				over1k++;
			}
		}
		System.out.println(" number of trials over 1,000 	: " + ((double) over1k / TRIALS_TO_RUN));
		System.out.println(" number of trials over 500   	: " + ((double) over500 / TRIALS_TO_RUN));
		System.out.println(" number of trials over 100   	: " + ((double) over100 / TRIALS_TO_RUN));
		System.out.println(" number of trials over 50       : " + ((double) over50 / TRIALS_TO_RUN));
		System.out.println(" number of trials less than 50  : " + ((double) under50 / TRIALS_TO_RUN));
		System.out.println(" the player met his goal : " + retierCount + " player retire rate is : " + ((double) retierCount /TRIALS_TO_RUN));
	}
}
