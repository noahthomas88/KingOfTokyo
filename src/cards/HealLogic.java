package cards;

import game.Player;

public class HealLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addHealth(2);

	}

}
