package cards;

import java.util.ArrayList;

import game.Player;

public class HighAltitudeBombingLogic implements CardLogic {

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		for(int i = 0; i < players.size(); i++){
			players.get(i).addHealth(-3);
		}

	}

}
