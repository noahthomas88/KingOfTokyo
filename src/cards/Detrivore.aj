package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect Detrivore {

	pointcut callDiceRolled(ArrayList<Dice> dice, Messages message, Gameplay gameplay)  : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dice, message) && target(gameplay) ;

	void around(ArrayList<Dice> dice, Messages message, Gameplay gameplay) : callDiceRolled(dice,message, gameplay){
		if(gameplay.currentplayer.haveCard("Detrivore")){

			ArrayList<Dice> otherdice = new ArrayList<Dice>();
			int attack = 0;
			int heal = 0;
			int energy = 0;
			
			for (Dice die : dice) {
				String result = gameplay.gameUI.numberToString(die.numberRolled);
				if (result.equals(message.getString("GUI.64"))) {
					attack++;
				} else if (result.equals(message.getString("GUI.65"))) {
					heal++;
				} else if (result.equals(message.getString("GUI.62"))) {
					energy++;
				} else {
					otherdice.add(die);
				}
			}
			
			gameplay.gameboard.doAttack(gameplay.currentplayer, -attack);
			gameplay.gameUI.EnableCedeButton();
			if (gameplay.currentplayer != gameplay.gameboard.cityPlayer){
				gameplay.currentplayer.addHealth(heal);
			}
			gameplay.currentplayer.addEnergy(energy);
			if(!otherdice.isEmpty()){
				gameplay.currentplayer.addVictory(2);
			}
			gameplay.calculateScore(otherdice);
			gameplay.gameUI.EnableEndTurnButton();
			gameplay.gameUI.updatePlayerText(gameplay.gameboard);
			gameplay.gameUI.DisableRollButton();
			gameplay.checkWin();
		}
		else{
			proceed(dice,message,gameplay);
		}
	}
}
