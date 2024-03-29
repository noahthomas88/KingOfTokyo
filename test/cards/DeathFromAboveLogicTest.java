package cards;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;
import main.GUI;

public class DeathFromAboveLogicTest {

	@Test
	public void useCard() {
		DeathFromAboveLogic logic = new DeathFromAboveLogic();
		Card card = EasyMock.strictMock(Card.class);
		Player player = EasyMock.strictMock(Player.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		
		player.addToHand(card);
		player.addVictory(2);
		gui.moveToTokyo(player);
		
		EasyMock.replay(player, gui);
		
		card.logic = logic;
		player.addToHand(card);
		logic.use(player, null, gui);
		
		EasyMock.verify(player, gui);
	}

}
