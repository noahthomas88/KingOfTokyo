package cards;

import java.util.ArrayList;

import game.Player;
import main.GUI;

public class SmokeCloudLogic extends CardLogic {
	
	public int count;
	
	public SmokeCloudLogic() {
		this.count = 3;
	}
	
	public void use(Player player, ArrayList<Player> players, GUI gui) {
		player.extraRoll++;
		count--;
		if(count <= 0) {
			player.removeCard("Smoke Cloud");
		}
	}
}
