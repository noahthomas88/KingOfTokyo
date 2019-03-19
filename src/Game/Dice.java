package Game;

import java.util.Random;

public class Dice {
	
	private boolean isEmpty;

	public Dice(){
		Random diceRoller = new Random();
	}

	public int roll() {
		Random diceRoller = new Random();
		return diceRoller.nextInt(6) + 1;
	}

	public int timesRolled() {
		return 0;
	}
}
