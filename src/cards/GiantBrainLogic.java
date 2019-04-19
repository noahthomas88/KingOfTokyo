package cards;

import java.util.ArrayList;

import game.Player;

public class GiantBrainLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.numberOfDieRolls++;

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		// TODO Auto-generated method stub
		
	}

}
