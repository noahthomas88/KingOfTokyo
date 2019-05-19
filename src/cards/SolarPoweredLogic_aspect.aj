package cards;

import game.Gameplay;
import game.Player;

public aspect SolarPoweredLogic_aspect {

	pointcut callEndTurn(Gameplay game) : execution(void Gameplay.endTurn()) && args() && target(game);

	before(Gameplay game) : callEndTurn(game) {
		if (game.currentplayer.energy <= 0 && game.currentplayer.haveCard("Solar Powered")) {
			game.currentplayer.addEnergy(1);
		}
	}

}
