package cards;

import java.util.ArrayList;

import game.Player;

public class ExtraHeadLogic implements CardLogic {

	@Override
	public void use(Player player) {
		player.addOneDie();
	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		// TODO Auto-generated method stub
		
	}

}
