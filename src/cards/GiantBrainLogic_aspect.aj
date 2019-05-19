package cards;

import game.Player;

public aspect GiantBrainLogic_aspect {

	pointcut giantBrain(Player player) : execution(int Player.getNumberOfRolls()) && target(player);

	before(Player player): giantBrain(player) {
		if(player.haveCard("Giant Brain")) {
			player.extraRoll++;
		}
	}
}
