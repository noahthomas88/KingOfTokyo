package cards;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Gameplay;
import game.Player;

public class SolarPoweredLogicTest {

	@Test
	public void testEndTurnNoEnergyHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Solar Powered";
		player.energy = 0;
		player.addToHand(card);

		Gameplay game = new Gameplay(null, player, null, null, null);
		game.currentplayer = player;
		game.endTurn();
		
		assertEquals(player.energy, 1);
	}

	@Test
	public void testEndTurnWithEnergyHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Solar Powered";
		player.energy = 3;
		player.addToHand(card);
		
		Gameplay game = new Gameplay(null, player, null, null, null);
		game.currentplayer = player;
		game.endTurn();
		
		assertEquals(player.health, 9);
	}

}
