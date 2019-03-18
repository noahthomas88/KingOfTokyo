package Game;

import java.util.Random;

public class Dice {
	
	private boolean isEmpty;

	public Dice(){
		this.isEmpty = true;
	}
	
	public boolean isEmpty(){
		return this.isEmpty;
	}

	public int roll() {
		// TODO Auto-generated method stub
		Random diceRoller = new Random();
		return diceRoller.nextInt(6) + 1;
	}
}
