package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect FreezeTimeLogic_aspect {

	after (ArrayList<Dice> dice, Messages message, Gameplay gameplay) : callDiceRolled(dice,message, gameplay){
		if(gameplay.currentplayer.haveCard("Freeze Time")){
			int onesCounter = 0;
			for(Dice die: dice){
				if(die.numberRolled == 1){
					onesCounter++;
				}
			}
			if(onesCounter <= 3){
				gameplay.currentplayer.extraDie--;
				gameplay.currentplayer.extraRoll = 3;
				gameplay.gameUI.DisableEndTurnButton();
				gameplay.gameUI.updatePlayerText(gameplay.gameboard);
				gameplay.gameUI.EnableRollButton();
			}
		}
	}
}
