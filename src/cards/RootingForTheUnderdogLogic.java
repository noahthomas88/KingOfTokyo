package cards;

import java.util.ArrayList;

import game.Player;

public class RootingForTheUnderdogLogic implements CardLogic {

	@Override
	public void use(Player player) {
		throw new UnsupportedOperationException("Single parameter use is not supported for this class");

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		int min = Integer.MAX_VALUE;
		for (Player p: players) {
			if (p.victoryPoints < min + 1) {
				 min = p.victoryPoints;
			}
		}
		if(player.victoryPoints == min){
			player.victoryPoints++;
		}
	}
}
