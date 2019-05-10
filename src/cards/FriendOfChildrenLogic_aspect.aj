package cards;

import game.Player;

public aspect FriendOfChildrenLogic_aspect {
	
	pointcut callAddEnergy(int amount, Player player) : execution(void Player.addEnergy(int)) && args(amount) && target(player);

	void around(int amount, Player player) : callAddEnergy(amount, player) {
		if (player.haveCard("Friend Of Children") && amount > 0) {
			proceed(amount + 1, player);
		} else {
			proceed(amount, player);
		}
	}

}
