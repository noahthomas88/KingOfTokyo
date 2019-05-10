package cards;

import game.Player;

public aspect ExtraHeadLogic_aspect {

	pointcut callGetNumberDie(int amount, Player player) : execution(int Player.getNumberOfDie(int)) && args(amount) && target(player);

	int around(int amount, Player player) : callGetNumberDie(amount, player) {
		if (player.haveCard("Extra Head")) {
			return proceed(7, player);
		} else {
			return proceed(6, player);
		}
	}
}
