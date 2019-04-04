package cards;

import game.Player;

public class ApartmentBuildingLogic implements CardLogic {

	public void use(Player p) {
		p.victoryPoints += 3;
	}

}
