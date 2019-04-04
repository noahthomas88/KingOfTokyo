package cards;

import game.Player;

public class CommuterTrainLogic extends CardLogic {

	public void use(Player p) {
		p.victoryPoints += 2;
	}

}
