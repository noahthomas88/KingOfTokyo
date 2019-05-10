package cards;

import java.util.ArrayList;

import game.Board;
import game.Player;

public class ApartmentBuildingLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addVictory(3);
	}

	@Override
	public void use(Player player, ArrayList<Player> players, Board board) {
		use(player);
	}

}
