package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class FlamethrowerLogic extends CardLogic {

	public void use(Player player, ArrayList<Player> players, GUI gui) {	
		for(int i = 0; i < players.size(); i++) {
			if(!players.get(i).equals(player)){
				players.get(i).addHealth(-2);
			}
		}
	}

}
