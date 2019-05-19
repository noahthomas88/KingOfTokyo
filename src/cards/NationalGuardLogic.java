package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class NationalGuardLogic extends CardLogic {
	
	public void use(Player player) {
		player.addHealth(-2);
		player.addVictory(2);
	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);	
	}

}
