package cards;

import game.Player;

public class ExtraHeadLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addOneDie();
	}

}
