package cards;

import game.Player;

public aspect GiantBrainLogic_aspect {

	pointcut giantBrain(Player player) : execution(int Player.getNumberOfRolls()) && target(player);

	before(Player player): giantBrain(player) {
		player.extraRoll++;
	}
}
