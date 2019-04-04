package cards;

import game.Player;

public class SkyscraperLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.victoryPoints += 4;

	}

}
