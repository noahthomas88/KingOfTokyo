package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class EnergyDrinkLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		if(player.energy > 0) {
			player.addEnergy(-1);
			player.extraRoll++;
		}
	}
}
