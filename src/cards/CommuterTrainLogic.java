package cards;

import game.Player;

public class CommuterTrainLogic implements CardLogic {

	public void use(Player p) {
		p.victoryPoints += 2;
	}

}
