package cards;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;

public class FriendOfChildrenLogicTest {
	
	@Test
	public void testAddOneEnergyHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Friend Of Children";
		player.energy = 4;
		player.addToHand(card);
		player.addEnergy(1);
		assertEquals(player.energy, 6);
	}
	
	@Test
	public void testAddTwoEnergyHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Friend Of Children";
		player.energy = 6;
		player.addToHand(card);
		player.addEnergy(2);
		assertEquals(player.energy, 9);
	}
	
	@Test
	public void testLoseOneEnergyHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Friend Of Children";
		player.energy = 8;
		player.addToHand(card);
		player.addEnergy(-1);
		assertEquals(player.energy, 7);
	}

}
