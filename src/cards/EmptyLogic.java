package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class EmptyLogic implements CardLogic {

	@Override
	public void use(Player player) {
		//do nothing, used for cards with aspect
	}

	@Override
	public void use(Player player, ArrayList<Player> players, GUI gui) {
		//do nothing
	}

}
