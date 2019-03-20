package Game;

import java.util.Random;

public class Dice {
	
	private boolean isEmpty;
	private int timesRolled;
	public boolean isResolved;
	public int numberRolled;

	public Dice(){
		this.timesRolled = 0;
		this.isResolved = false;
		this.numberRolled = 1;
	}

	public int roll() {
		if(this.timesRolled > 3){
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
		this.numberRolled = diceRoller .nextInt(6) + 1;
		return this.numberRolled;
		}

	public int getTimesRolled() {
		return this.timesRolled;
	}
}
