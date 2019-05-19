package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class StretchyLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		if(gui.checkBeforeResolve() && gui.isCurrentPlayer(player)) {
			String die = gui.chooseDice();
			if (gui.checkDieNumber(die)) {
				String changeTo = gui.inputChange();
				if (gui.checkNumber(changeTo) && player.energy > 1) {
					player.addEnergy(-2);
					gui.updateDie(die, changeTo);
				}
			}
		}
	}
}
