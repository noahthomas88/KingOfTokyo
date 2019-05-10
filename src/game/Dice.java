package game;

import java.util.Random;

public class Dice {

	private boolean isEmpty;
	private int timesRolled;
	public boolean isResolved;
	public int numberRolled;
	private int rollLimit;

	public Dice(Player player) {
		this.timesRolled = 0;
		this.isResolved = false;
		this.numberRolled = 1;
		this.rollLimit = player.getNumberOfRolls();
	}

	public int roll() {
		if (this.timesRolled >= rollLimit) {
			this.isResolved = true;
		}
		if (this.isResolved) {
			throw new UnsupportedOperationException(
					String.format("this dice has been resolved and cannot be rolled again", this.getClass().getName()));
		}
		this.timesRolled++;
		Random diceRoller = new Random();
		this.numberRolled = diceRoller.nextInt(6) + 1;
		return this.numberRolled;
	}
	
	public String numberToString(int number) {
		if(number < 4) {
			return number + "";
		} else if (number == 4){
			return "attack";
		} else if (number == 5) {
			return "energy";
		} else {
			return "heal";
		}
	}

}
