package cards;

import game.Gameplay;

public aspect MediaFriendlyLogic_aspect {
	
	pointcut callBuyCard(int num, Gameplay game) : execution(void Gameplay.buyCard(int)) && args(num) && target(game);

	before(int num, Gameplay game) : callBuyCard(num, game) {
		if (game.currentplayer.energy >= game.deck.visibleCard[num-1].cost && game.currentplayer.haveCard("Media Friendly") ) {
			game.currentplayer.addVictory(1);
		}
	}
}
