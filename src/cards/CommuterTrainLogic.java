package cards;

import java.util.ArrayList;

import game.Player;

public class CommuterTrainLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addVictory(2);
	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		// TODO Auto-generated method stub
		
	}

}
