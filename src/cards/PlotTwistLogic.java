package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class PlotTwistLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {
		if (gui.checkBeforeResolve() && gui.isCurrentPlayer(player)) {
			gui.usePlotTwist();
		}
	}

}
