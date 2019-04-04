package cards;

import game.Player;

public class ExtraHeadLogic extends CardLogic {

	public void use(Player p) {
		p.addOneDie();
	}

}
