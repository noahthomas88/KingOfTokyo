package cards;

import java.util.ArrayList;

import game.Board;
import game.Player;

public class EnergizeLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addEnergy(9);

	}

	@Override
	public void use(Player player, ArrayList<Player> players, Board board) {
		use(player);	
	}

}
