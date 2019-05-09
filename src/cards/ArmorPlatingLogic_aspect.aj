package cards;

import game.Player;

public aspect ArmorPlatingLogic_aspect {

pointcut callAddHealth(int amount, Player player) : execution(void Player.addHealth(int)) && args(amount) && target(player);
	
	before(int amount, Player player) : callAddHealth(amount, player) {}
	
	void around(int amount, Player player) : callAddHealth(amount, player) {
		if (amount == -1 && player.haveCard("Armor Plating")) {
			player.addHealth(1);
		}else {
			proceed(amount, player);
		}
	}
	
	after(int amount, Player player) : callAddHealth(amount, player) {}
}
