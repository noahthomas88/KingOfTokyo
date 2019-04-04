package cards;

import game.Player;

public class NuclearPowerPlantLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.victoryPoints += 2;
		player.health += 3;
	}

}
