package cards;

import java.util.ArrayList;

import game.Player;

public class FlamethrowerLogic implements CardLogic {

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		
		for(int i = 0; i < players.size(); i++) {
			if(!players.get(i).equals(player)){
				players.get(i).addHealth(-2);
			}
		}

	}

}
