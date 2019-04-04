package cards;

import game.Player;

public class ApartmentBuildingLogic extends CardLogic {

	public void use(Player p) {
		p.victoryPoints += 3;
	}

}
