package cards;

import java.util.ArrayList;

import game.Player;

public class ArmorPlatingLogic implements CardLogic {

	@Override
	public void use(Player player) {
		throw new UnsupportedOperationException("Single parameter use is not supported for this class");

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		throw new UnsupportedOperationException("Double parameter use is not supported for this class");

	}

}
