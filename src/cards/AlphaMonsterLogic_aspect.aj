package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect AlphaMonsterLogic_aspect {
	pointcut callDiceRolled(ArrayList<Dice> dicelist, Messages message, Gameplay game) : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dicelist, message) && target(game);

	before(ArrayList<Dice> dicelist, Messages message, Gameplay game) : callDiceRolled(dicelist, message, game) {
		if (game.currentplayer.haveCard("Alpha Monster")){
			for(Dice d : dicelist) {
				if (d.numberRolled == 4) {
					game.currentplayer.addVictory(1);
					break;
				}
			}
		}
	}
}
