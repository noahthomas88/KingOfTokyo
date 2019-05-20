package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class BatteryMonsterLogic extends CardLogic {
	
	public void use(Player player) {
		player.energyStore = 6;
	}

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		use(player);
	}

}
