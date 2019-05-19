package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class EvenBiggerLogic extends CardLogic{
	
	public void use(Player player) {
		player.maxHealth = 12;
	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);
	}

}
