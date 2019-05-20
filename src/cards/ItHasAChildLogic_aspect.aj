package cards;

import java.util.ArrayList;

import game.Player;

public aspect ItHasAChildLogic_aspect {
	
	pointcut callPlayerDeath(Player player) : execution(void Player.playerDeath()) && args() && target(player);

	void around(Player player) : callPlayerDeath(player) {
		if(player.haveCard("It Has a Child")){
			for(int i = 0; i < player.cardsInHand.size(); i++) {
				player.removeCard(player.cardsInHand.get(i).name);
			}
			player.health = 10;
			player.victoryPoints = 0;
			player.maxHealth = 10;
			player.playerDeathThisTurn = false;
		} else {
			proceed(player);
		}
	}

}
