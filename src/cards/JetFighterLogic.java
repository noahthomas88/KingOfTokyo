package cards;

import game.Player;

public class JetFighterLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addHealth(-4);
		player.addVictory(5);

	}

}
