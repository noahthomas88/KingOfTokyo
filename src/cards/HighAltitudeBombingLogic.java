package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class HighAltitudeBombingLogic implements CardLogic {

	@Override
	public void use(Player player) {
		throw new UnsupportedOperationException("Single parameter use is not supported for this class");
	}

	@Override
	public void use(Player player, ArrayList<Player> players, GUI gui) {
		for(int i = 0; i < players.size(); i++){
			players.get(i).addHealth(-3);
		}
	}

}
