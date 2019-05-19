package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class NuclearPowerPlantLogic extends CardLogic {

	public void use(Player player) {
		player.addVictory(2);
		player.addHealth(3);
	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);
	}

}
