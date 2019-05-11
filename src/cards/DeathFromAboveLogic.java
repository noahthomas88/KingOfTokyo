package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class DeathFromAboveLogic implements CardLogic {

	@Override
	public void use(Player player) {
		throw new UnsupportedOperationException("Single parameter use is not supported for this class");
	}

	@Override
	public void use(Player player, ArrayList<Player> players, GUI gui) {
		System.out.println("HI");
		player.addVictory(2);
	}

}
