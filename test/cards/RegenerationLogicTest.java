package cards;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;

public class RegenerationLogicTest {
	
	@Test
	public void testAddOneHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Regeneration";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(1);
		assertEquals(player.health, 10);
	}
	
	@Test
	public void testAddTwoHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Regeneration";
		player.health = 6;
		player.addToHand(card);
		player.addHealth(2);
		assertEquals(player.health, 9);
	}
	
	@Test
	public void testLoseOneHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Regeneration";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(-1);
		assertEquals(player.health, 7);
	}

}
