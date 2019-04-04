package cards;

import game.Player;

public class CornerStoreLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.victoryPoints++;

	}

}
