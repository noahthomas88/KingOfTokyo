package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect CompleteDestructionLogic_aspect {

	pointcut callDiceRolled(ArrayList<Dice> dicelist, Messages message, Gameplay game) : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dicelist, message) && target(game);

	before(ArrayList<Dice> dicelist, Messages message, Gameplay game) : callDiceRolled(dicelist, message, game) {
		int[] count = new int[6];
		if (game.currentplayer.haveCard("Complete Destruction")){
			for(Dice d : dicelist) {
				count[d.numberRolled-1]++;
			}
			int all = 0;
			for(int i : count) {
				if(i == 1) {
					all++;
				}
			}
			if(all == 6) {
				game.currentplayer.addVictory(9);
			}
		}
	}
}
