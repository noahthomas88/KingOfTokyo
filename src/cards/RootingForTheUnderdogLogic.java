package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class RootingForTheUnderdogLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {
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
