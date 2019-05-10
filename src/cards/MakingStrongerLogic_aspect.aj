package cards;

import game.Player;

public aspect MakingStrongerLogic_aspect {
	
	pointcut callAddHealth(int amount, Player player) : execution(void Player.addHealth(int)) && args(amount) && target(player);

	void around(int amount, Player player) : callAddHealth(amount, player) {
		proceed(amount, player);
	}
	
	after(int amount, Player player) : callAddHealth(amount, player) {
		if (amount <= 0 && player.haveCard("We're only making it stronger")) {
			player.addEnergy(-amount/2);
		}
	}
	
	
}
