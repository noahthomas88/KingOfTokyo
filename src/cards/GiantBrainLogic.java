package cards;

import game.Player;

public class GiantBrainLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.numberOfDieRolls++;

	}

}
