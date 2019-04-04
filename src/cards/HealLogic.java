package cards;

import game.Player;

public class HealLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.health += 2;

	}

}
