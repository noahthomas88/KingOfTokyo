package cards;

import game.Gameplay;

public aspect BatteryMonsterLogic_aspect {
	
	pointcut callBeginTurn(Gameplay game) : execution(void Gameplay.beginTurn()) && args() && target(game);

	after(Gameplay game) : callBeginTurn(game) {
		if(game.currentplayer.haveCard("Battery Monster")) {
			if(game.currentplayer.energyStore > 0) {
				game.currentplayer.addEnergy(2);
				game.currentplayer.energyStore = game.currentplayer.energyStore - 2;
			} else {
				game.currentplayer.removeCard("Battery Monster");
			}
		}
	}

}
