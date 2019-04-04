package cards;

import game.Player;

public class ExtraHeadLogic implements CardLogic {

	public void use(Player p) {
		p.addOneDie();
	}

}
