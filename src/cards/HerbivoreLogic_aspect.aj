package cards;

import game.Gameplay;
import game.Player;

public aspect HerbivoreLogic_aspect {
	
	boolean hurtOthers = false;
	
	pointcut callAddHealth(int amount, Player player) : execution(void Player.addHealth(int)) && args(amount) && target(player);

	void around(int amount, Player player) : callAddHealth(amount, player) {
		if (player.haveCard("Herbivore") && amount < 0) {
			hurtOthers = true;
			proceed(amount, player);
		} else {
			proceed(amount, player);
		}
	}
	
	pointcut callEndTurn(Gameplay game) : execution(void Gameplay.endTurn()) && args() && target(game);

	before(Gameplay game) : callEndTurn(game) {
		if (game.currentplayer.haveCard("Herbivore") && !hurtOthers) {
			game.currentplayer.addVictory(1);
		}
		hurtOthers = false;
	}

}
