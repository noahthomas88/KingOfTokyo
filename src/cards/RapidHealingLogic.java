package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class RapidHealingLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		if(player.energy > 1) {
			player.addEnergy(-2);
			player.addHealth(1);
		}
	}
}
