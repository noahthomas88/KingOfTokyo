package cards;

import game.Player;

public aspect MakingStrongerLogic_aspect {
	
	pointcut callAddHealth(int amount, Player player) : call(void Player.addHealthHelper(int)) && args(amount) && target(player);
	
	before(int amount, Player player) : callAddHealth(amount, player) {}
	
	void around(int amount, Player player) : callAddHealth(amount, player) {}
	
	after(int amount, Player player) : callAddHealth(amount, player) {
		if (amount == -2 && player.haveCard("We're only making it stronger")) {
			player.addEnergy(1);
		}
	}
}
