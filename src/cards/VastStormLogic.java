package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class VastStormLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		for (int i = 0; i < players.size(); i++) {
			int originalEnergy = players.get(i).energy;
			if (players.get(i).equals(player)) {
				player.addVictory(2);
			} else {
				players.get(i).addEnergy(-originalEnergy/2);
			}
		}
	}
}
