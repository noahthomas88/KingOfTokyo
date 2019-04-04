package cards;

import game.Player;

public class NuclearPowerPlantLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addVictory(2);
		player.addHealth(3);
	}

}
