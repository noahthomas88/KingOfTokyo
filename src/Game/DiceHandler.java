package Game;

import java.util.ArrayList;

public class DiceHandler {

	private ArrayList<Dice> dice;
	
	public DiceHandler(Player player){
		int numberOfDie = player.numberOfDieToRoll;
		this.dice = new ArrayList<Dice>();
		for(int i = 0; i < numberOfDie ;i++){
			Dice die = new Dice();
			this.dice.add(die);
		}
	}

	public int numberOfDie() {
		// TODO Auto-generated method stub
		return dice.size();
	}

}
