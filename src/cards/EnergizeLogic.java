package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class EnergizeLogic extends CardLogic {

	public void use(Player player) {
		player.addEnergy(9);

	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);	
	}

}
