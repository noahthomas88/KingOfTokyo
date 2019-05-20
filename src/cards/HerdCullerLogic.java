package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class HerdCullerLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		if(gui.checkBeforeResolve() && gui.isCurrentPlayer(player) && player.herdCuller) {
			String die = gui.chooseDice();
			if (gui.checkDieNumber(die)) {
				gui.updateDie(die, "1");
				player.herdCuller = false;
			}
		}
	}
}
