package cards;

import game.Player;

public class TankLogic implements CardLogic {

	public void use(Player player) {
		player.health -= 3;
		player.victoryPoints += 4;
	}

}
