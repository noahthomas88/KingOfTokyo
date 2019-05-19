package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class TankLogic extends CardLogic {

	public void use(Player player) {
		player.addHealth(-3);
		player.addVictory(4);
	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);	
	}

}
