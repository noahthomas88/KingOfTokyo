package Game;

import java.util.Random;

public class Dice {
	
	private boolean isEmpty;

	public Dice(){
		Random diceRoller = new Random();
	}

	public int roll() {
		// TODO Auto-generated method stub
		Random diceRoller = new Random();
		return diceRoller.nextInt(6) + 1;
	}
}
