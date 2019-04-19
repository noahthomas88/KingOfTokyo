package cards;

import java.util.ArrayList;

import game.Player;

public interface CardLogic {

	void use(Player player);
	
	void use(Player player, ArrayList<Player> players);

}
