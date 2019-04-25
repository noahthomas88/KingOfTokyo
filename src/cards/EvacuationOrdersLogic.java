package cards;

import java.util.ArrayList;

import game.Player;

public class EvacuationOrdersLogic implements CardLogic {

	@Override
	public void use(Player player) {
		

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		
		for(int i = 0; i < players.size(); i++) {
			if(!players.get(i).equals(player)){
				players.get(i).addVictory(-5);
			}
		}

	}

}
