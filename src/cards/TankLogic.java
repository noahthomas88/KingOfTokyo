package cards;

import game.Player;

public class TankLogic implements CardLogic {

	public void use(Player player) {
		player.addHealth(-3);
		player.addVictory(4);
	}

}
