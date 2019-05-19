package cards;

import game.Gameplay;

public aspect EnergyHoarderLogic_aspect {
	
	pointcut callEndTurn(Gameplay game) : execution(void Gameplay.endTurn()) && args() && target(game);

	before(Gameplay game) : callEndTurn(game) {
		if (game.currentplayer.haveCard("Energy Hoarder")) {
			int victoryPerSix = game.currentplayer.energy / 6;
			game.currentplayer.addVictory(victoryPerSix);
		}
	}
}
