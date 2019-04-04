package cards;

import game.Player;

public class NationalGuardLogic implements CardLogic {
	
	@Override
	public void use(Player player) {
		player.addHealth(-2);
		player.addVictory(2);
	}

}
