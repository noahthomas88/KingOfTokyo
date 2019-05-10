package cards;

import game.Player;

public aspect GiantBrainLogic_aspect {

	pointcut giantBrain(int amount, Player player) : call(int Player.checkGiantBrain(int)) && args(amount) && target(player);

	int around(int amount, Player player) : giantBrain(amount, player) {
		if (player.haveCard("Giant Brain")) {
			return amount + 1;
		} else {
			return amount;
		}
	}
}
