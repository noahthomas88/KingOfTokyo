package cards;

import game.Player;

public aspect ExtraHeadLogic_aspect {

	pointcut extraHead(Player player) : execution(int Player.getNumberOfDie()) && target(player);

	int around(Player player): extraHead(player) {
		return player.haveCard("Extra Head")? proceed(player) + 1 : proceed(player);
	}
}
