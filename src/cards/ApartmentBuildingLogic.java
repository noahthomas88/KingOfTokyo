package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class ApartmentBuildingLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addVictory(3);
	}

	@Override
	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);
	}

}
