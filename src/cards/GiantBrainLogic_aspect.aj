package cards;

import game.Player;

public aspect GiantBrainLogic_aspect {

	pointcut giantBrain(Player player) : execution(int Player.getNumberOfRolls()) && target(player);

	int around(Player player): giantBrain(player) {
		return player.haveCard("Giant Brain")? proceed(player) + 1 : proceed(player);
	}
}
