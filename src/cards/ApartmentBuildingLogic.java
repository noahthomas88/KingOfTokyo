package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class ApartmentBuildingLogic extends CardLogic {
	
	public void use(Player player) {
		player.addVictory(3);
	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);
	}

}
