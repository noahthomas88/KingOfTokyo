package cards;

import java.util.ArrayList;

import game.Player;

public class EnergizeLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addEnergy(9);

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		// TODO Auto-generated method stub
		
	}

}
