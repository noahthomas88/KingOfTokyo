package cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;

public class ArmorPlatingLogicTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testUse() {
		ArmorPlatingLogic logic = new ArmorPlatingLogic();
		logic.use(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUse2() {
		ArmorPlatingLogic logic = new ArmorPlatingLogic();
		logic.use(null, null);
	}
	
	@Test
	public void testLoseOneHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Armor Plating";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(-1);
		assertEquals(player.health, 8);
	}
	
	@Test
	public void testLoseTwoHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Armor Plating";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(-2);
		assertEquals(player.health, 6);
	}
	
	@Test
	public void testAddOneHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Armor Plating";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(1);
		assertEquals(player.health, 9);
	}

}
