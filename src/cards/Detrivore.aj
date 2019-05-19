package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect Detrivore {

	pointcut callDiceRolled(ArrayList<Dice> dice, Messages message, Gameplay gameplay)  : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dice, message) && target(gameplay) ;

	after (ArrayList<Dice> dice, Messages message, Gameplay gameplay) : callDiceRolled(dice,message, gameplay){
		gameplay.currentplayer.addVictory(2);
	}
}
