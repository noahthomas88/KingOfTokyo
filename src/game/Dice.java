package game;

import java.util.Random;

public class Dice {
	public int numberRolled;
	
	public Dice(Player player) {
		this.numberRolled = 1;
	}

	public int roll() {
		Random diceRoller = new Random();
		this.numberRolled = diceRoller.nextInt(6) + 1;
		return this.numberRolled;
	}

}
