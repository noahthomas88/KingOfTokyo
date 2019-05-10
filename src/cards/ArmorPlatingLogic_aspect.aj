package cards;

import game.Player;

public aspect ArmorPlatingLogic_aspect {

pointcut callAddHealth(int amount, Player player) : execution(void Player.addHealth(int)) && args(amount) && target(player);
	
	void around(int amount, Player player) : callAddHealth(amount, player) {
		if (!(amount == -1) || !player.haveCard("Armor Plating")) {
			proceed(amount, player);
		}
	}
}
