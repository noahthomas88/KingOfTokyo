package Game;

import java.util.Random;

public class Dice {
	
	private boolean isEmpty;
	private int timesRolled;

	public Dice(){
		this.timesRolled = 0;
	}

	public int roll() {
		this.timesRolled++;
		Random diceRoller = new Random();
		return diceRoller .nextInt(6) + 1;
	}

	public int getTimesRolled() {
		return this.timesRolled;
	}
}
