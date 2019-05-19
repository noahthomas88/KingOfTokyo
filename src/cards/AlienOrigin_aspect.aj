package cards;

import game.Gameplay;

public aspect AlienOrigin_aspect {
	// Buying power cards costs you 1 less energy point.
	pointcut callBuyCard(int number, Gameplay gameplay) : execution(void Gameplay.buyCard(int)) && args(number) && target(gameplay);

	void around(int number, Gameplay gameplay) : callBuyCard(number, gameplay) {
		if (gameplay.currentplayer.haveCard("Alien Origin")) {
			Card tobuy = gameplay.deck.visibleCard[number - 1];
			if (gameplay.currentplayer.energy >= tobuy.cost - 1) {
				Card card = gameplay.deck.buy(number - 1);
				gameplay.currentplayer.addEnergy(-tobuy.cost + 1);
				gameplay.currentplayer.addToHand(card);
				if (card.type.equals("Discard")) {
					gameplay.useCard(card.name);
				}
				gameplay.gameUI.setCards(gameplay.deck.visibleCard);
				gameplay.gameUI.updatePlayerText(gameplay.gameboard);
			} else {
				gameplay.gameUI.energyWarning();
			}
		} else {
			proceed(number, gameplay);
		}
	}
}
