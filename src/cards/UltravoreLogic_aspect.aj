package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect UltravoreLogic_aspect {

	pointcut callBeginTurn(Gameplay game) : execution(void Gameplay.beginTurn()) && target(game);
	pointcut callDiceRolled(ArrayList<Dice> dicelist, Messages message, Gameplay game) : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dicelist, message) && target(game);

	before(Gameplay game) : callBeginTurn(game) {
		if (game.gameboard.cityPlayer == game.currentplayer && game.currentplayer.haveCard("Ultravore")){
			game.currentplayer.addVictory(1);
		}
	}
	
	before(ArrayList<Dice> dicelist, Messages message, Gameplay game) : callDiceRolled(dicelist, message, game) {
		if (game.gameboard.cityPlayer == game.currentplayer && game.currentplayer.haveCard("Ultravore")){
			for(Dice d:dicelist) {
				if (d.numberRolled == 4) {
					Dice attack = new Dice(game.currentplayer);
					attack.numberRolled = 4;
					dicelist.add(attack);
					break;
				}
			}
		}
	}
}
