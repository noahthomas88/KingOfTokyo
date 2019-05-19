package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class HealLogic extends CardLogic {

	public void use(Player player) {
		player.addHealth(2);
	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);
	}

}
