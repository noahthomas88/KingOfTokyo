package cards;

import game.Player;

public aspect RegenerationLogic_aspect {
	
	pointcut callAddHealth(int amount, Player player) : execution(void Player.addHealth(int)) && args(amount) && target(player);

	void around(int amount, Player player) : callAddHealth(amount, player) {
		if (player.haveCard("Regeneration") && amount > 0) {
			proceed(amount + 1, player);
		} else {
			proceed(amount, player);
		}
	}
}
