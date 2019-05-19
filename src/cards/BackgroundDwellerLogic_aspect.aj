package cards;

import main.DicePanel;

public aspect BackgroundDwellerLogic_aspect {

	pointcut callnextRow(DicePanel dicePanel) : execution(void DicePanel.nextRow()) && target(dicePanel);
	
	before (DicePanel dicePanel): callnextRow(dicePanel){
		dicePanel.rerow3s();
	}
	

}
