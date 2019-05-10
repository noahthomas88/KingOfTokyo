package cards;

import game.Player;

public aspect ExtraHeadLogic_aspect {

	pointcut extraHead(int amount, Player player) : call(int Player.checkExtraHead(int)) && args(amount) && target(player);

	int around(int amount, Player player) : extraHead(amount, player) {
		if (player.haveCard("Extra Head")) {
			return amount + 1;
		} else {
			return amount;
		}
	}
}
