package cards;

import game.Player;

public aspect EvenBiggerLogic_aspect {
	
	pointcut callRemoveCard(String string, Player player) : execution(void Player.removeCard(String)) && args(string) && target(player);

	void around(String string, Player player) : callRemoveCard(string, player) {
		if (player.haveCard("Even Bigger") && player.maxHealth != 10) {
			player.subMaxHealth();
			player.subMaxHealth();
			player.removeCard("Even Bigger");
		} else {
			proceed(string, player);
		}
	}

}
