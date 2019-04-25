package cards;

import java.util.ArrayList;

import game.Player;

public class RootingForTheUnderdogLogic implements CardLogic {

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		int min = Integer.MAX_VALUE;
		for (Player p: players) {
			if (p.victoryPoints <= min) {
				 min = p.victoryPoints;
			}
		}
		if(player.victoryPoints == min){
			player.victoryPoints++;
		}
	}
}
