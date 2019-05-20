package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect BurrowingLogic_aspect {

	pointcut callDiceRolled(ArrayList<Dice> dicelist, Messages message, Gameplay game) : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dicelist, message) && target(game);

	before(ArrayList<Dice> dicelist, Messages message, Gameplay game) : callDiceRolled(dicelist, message, game) {
		if (game.gameboard.cityPlayer == game.currentplayer && game.currentplayer.haveCard("Burrowing")){
					Dice attack = new Dice(game.currentplayer);
					attack.numberRolled = 4;
					dicelist.add(attack);
		}
	}
}
