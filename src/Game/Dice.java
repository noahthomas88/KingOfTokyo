package Game;

import java.util.Random;

public class Dice {
	
	private boolean isEmpty;
	private int timesRolled;
	public boolean isResolved;

	public Dice(){
		this.timesRolled = 0;
		this.isResolved = false;
	}

	public int roll() {
		if(this.timesRolled == 3){
			this.isResolved = true;
		}
		if(this.isResolved){
		       throw new UnsupportedOperationException(
		                String.format("this dice has been resolved and cannot be rolled again",
		                        this.getClass().getName())
		        );
		}
		this.timesRolled++;
		Random diceRoller = new Random();
		return diceRoller .nextInt(6) + 1;
		}

	public int getTimesRolled() {
		return this.timesRolled;
	}
}
