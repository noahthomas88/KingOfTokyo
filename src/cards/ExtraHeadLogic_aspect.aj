package cards;

import game.Player;

public aspect ExtraHeadLogic_aspect {

	pointcut extraHead(Player player) : execution(int Player.getNumberOfDie()) && target(player);

	before(Player player): extraHead(player) {
		if(player.haveCard("Extra Head")) {
			player.extraDie++;
		}
	}
}
