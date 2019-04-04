package cards;

import game.Player;

public class ApartmentBuildingLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addVictory(3);
	}

}
