package cards;

import java.util.ArrayList;

import game.Dice;
import game.Gameplay;
import main.Messages;

public aspect AcidAttackLogic_aspect {
	
	pointcut callDiceRolled(ArrayList<Dice> dice, Messages message, Gameplay gameplay)  : execution(void Gameplay.diceRolled(ArrayList<Dice>, Messages)) && args(dice, message) && target(gameplay) ;

	before(ArrayList<Dice> dice, Messages message, Gameplay gameplay) : callDiceRolled(dice,message, gameplay){
		if(gameplay.currentplayer.haveCard("Acid Attack")){
			Dice attackDice = new Dice(gameplay.currentplayer);
			attackDice.numberRolled = 4;
			dice.add(attackDice);
		}
	}
	after(ArrayList<Dice> dice, Messages message, Gameplay gameplay) : callDiceRolled(dice,message, gameplay){
		if(gameplay.currentplayer.haveCard("Acid Attack")){
			dice.remove(0);	
		}
	}
}
