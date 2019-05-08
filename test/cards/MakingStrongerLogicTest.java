package cards;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;

public class MakingStrongerLogicTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testUse() {
		MakingStrongerLogic logic = new MakingStrongerLogic();
		logic.use(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUse2() {
		MakingStrongerLogic logic = new MakingStrongerLogic();
		logic.use(null, null);
	}
	
	@Test
	public void testLoseNoHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "We're only making it stronger";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(0);
		assertEquals(player.health, 8);
		assertEquals(player.energy, 0);
	}
	
	@Test
	public void testLoseTwoHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "We're only making it stronger";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(-2);
		assertEquals(player.health, 6);
		assertEquals(player.energy, 1);
	}
	
	@Test
	public void testLoseTwoHealthNoCard() {
		Player player = new Player("test");
		player.health = 8;
		player.addHealth(-2);
		assertEquals(player.health, 6);
		assertEquals(player.energy, 0);
	}
	
	@Test
	public void testLoseThreeHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "We're only making it stronger";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(-3);
		assertEquals(player.health, 5);
		assertEquals(player.energy, 1);
	}
	
	@Test
	public void testLoseFourHealthHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "We're only making it stronger";
		player.health = 8;
		player.addToHand(card);
		player.addHealth(-4);
		assertEquals(player.health, 4);
		assertEquals(player.energy, 2);
	}

}
