package cards;

import game.Player;

public class TankLogic implements CardLogic {

	public void use(Player p) {
		p.health -= 3;
		p.victoryPoints += 4;
	}

}
