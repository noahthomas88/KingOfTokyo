package cards;

import game.Player;

public class EnergizeLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addEnergy(9);

	}

}
