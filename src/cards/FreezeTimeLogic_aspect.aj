package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect FreezeTimeLogic_aspect {

	pointcut callDiceRolled(ArrayList<Dice> dice, Messages message, Gameplay gameplay)  : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dice, message) && target(gameplay) ;
	
	after (ArrayList<Dice> dice, Messages message, Gameplay gameplay) : callDiceRolled(dice,message, gameplay){
		if(gameplay.currentplayer.haveCard("Freeze Time")){
			int onesCounter = 0;
			for(Dice die: dice){
				if(die.numberRolled == 1){
					onesCounter++;
				}
			}
			if(onesCounter > 2){
				gameplay.redoTurn = true;
			}
		}
	}
}
