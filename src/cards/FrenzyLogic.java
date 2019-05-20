package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class FrenzyLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {	
		gui.frenzy();
	}
}
