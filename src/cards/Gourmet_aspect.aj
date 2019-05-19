package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect Gourmet_aspect {

	pointcut callDiceRolled(ArrayList<Dice> dice, Messages message, Gameplay gameplay)  : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dice, message) && target(gameplay) ;

	before(ArrayList<Dice> dice, Messages message, Gameplay gameplay) : callDiceRolled(dice,message, gameplay){
		if (gameplay.currentplayer.haveCard("Gourmet")) {
			int onesCounter = 0;
			for (Dice die : dice) {
				if (die.numberRolled == 1) {
					onesCounter++;
				}
			}
			if (onesCounter >= 3) {
				gameplay.currentplayer.addVictory(2);
			}
		}
	}
}
