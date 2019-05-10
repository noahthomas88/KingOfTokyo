package cards;

import java.util.ArrayList;

import game.Board;
import game.Player;

public class VastStormLogic implements CardLogic {

	@Override
	public void use(Player player) {
		throw new UnsupportedOperationException("Single parameter use is not supported for this class");
	}

	@Override
	public void use(Player player, ArrayList<Player> players, Board board) {
		for (int i = 0; i < players.size(); i++) {
			int originalEnergy = players.get(i).energy;
			if (players.get(i).equals(player)) {
				player.addVictory(2);
			} else {
				players.get(i).addEnergy(-originalEnergy/2);
			}
		}
	}
}
