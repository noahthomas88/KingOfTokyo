package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class DeathFromAboveLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		player.addVictory(2);
		gui.moveToTokyo(player);
	}

}
