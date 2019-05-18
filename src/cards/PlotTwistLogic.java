package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class PlotTwistLogic implements CardLogic {

	@Override
	public void use(Player player) {
		throw new UnsupportedOperationException("Single parameter use is not supported for this class");
	}

	@Override
	public void use(Player player, ArrayList<Player> players, GUI gui) {
		if (gui.checkBeforeResolve() && gui.isCurrentPlayer(player)) {
			gui.usePlotTwist();
		}
	}

}
