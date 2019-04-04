package cards;

import game.Player;

public class CommuterTrainLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.victoryPoints += 2;
	}

}
