package cards;

import java.util.ArrayList;

import game.Player;

public class HealLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addHealth(2);

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		use(player);
		
	}

}
