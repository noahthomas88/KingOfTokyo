package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class JetFighterLogic extends CardLogic {

	public void use(Player player) {
		player.addHealth(-4);
		player.addVictory(5);
	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);	
	}

}
