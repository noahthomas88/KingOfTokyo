package cards;

import java.util.ArrayList;

import game.Board;
import game.Player;

public class TankLogic implements CardLogic {

	public void use(Player player) {
		player.addHealth(-3);
		player.addVictory(4);
	}

	@Override
	public void use(Player player, ArrayList<Player> players, Board board) {
		use(player);	
	}

}
