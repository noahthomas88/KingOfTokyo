package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect PoisonQuills_aspect {

	pointcut callDiceRolled(ArrayList<Dice> dice, Messages message, Gameplay gameplay)  : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dice, message) && target(gameplay) ;

	before(ArrayList<Dice> dice, Messages message, Gameplay gameplay) : callDiceRolled(dice,message, gameplay){
		if (gameplay.currentplayer.haveCard("Poison Quills")) {
			int twosCounter = 0;
			for (Dice die : dice) {
				if (die.numberRolled == 2) {
					twosCounter++;
				}
			}
			if (twosCounter >= 3) {
				gameplay.gameboard.doAttack(gameplay.currentplayer, -2);
			}
		}
	}
}
