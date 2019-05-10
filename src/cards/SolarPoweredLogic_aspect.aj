package cards;

import game.Gameplay;
import game.Player;

public aspect SolarPoweredLogic_aspect {

	pointcut callEndTurn(Gameplay game, Player player) : execution(void Gameplay.endTurn()) && args() && target(player);

	void around(int amount, Player player) : callEndTurn(game, player) {
		proceed(amount, player);
	}

	after(int amount, Player player) : callEndTurn(game, player) {
		if (player.energy <= 0 && player.haveCard("Solar Powered")) {
			player.addEnergy(1);
		}
	}

}
