package cards;

import game.Player;

public aspect EaterOfTheDeadLogic_aspect {
	
	pointcut callPlayerDeath(Player player) : execution(void Player.playerDeath()) && target(player);

	after(Player player) : callPlayerDeath(player) {
		Player playerWithCard = null;
		
		for(int i = 0; i < player.playerList.size(); i++) {
			Player indexedPlayer = player.playerList.get(i);
			if(!indexedPlayer.name.equals(player.name) && indexedPlayer.haveCard("Eater of the Dead")) {
				playerWithCard = indexedPlayer;
			}
		}
		
		if(playerWithCard != null) {
			playerWithCard.addVictory(3);
		}
		player.playerDeathThisTurn = false;	
	}

}
