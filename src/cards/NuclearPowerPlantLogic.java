package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class NuclearPowerPlantLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addVictory(2);
		player.addHealth(3);
	}

	@Override
	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);
	}

}
