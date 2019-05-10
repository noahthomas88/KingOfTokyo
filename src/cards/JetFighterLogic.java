package cards;

import java.util.ArrayList;

import game.Board;
import game.Player;

public class JetFighterLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addHealth(-4);
		player.addVictory(5);
	}

	@Override
	public void use(Player player, ArrayList<Player> players, Board board) {
		use(player);	
	}

}
