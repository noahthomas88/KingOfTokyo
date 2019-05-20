package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class SmokeCloudLogicTest {

	@Test
	public void testSetUp() {
		SmokeCloudLogic logic = new SmokeCloudLogic();
		assertEquals(3, logic.count);
	}
	
	@Test
	public void testUse() {
		SmokeCloudLogic logic = new SmokeCloudLogic();
		Player player = new Player("test");
		logic.use(player, null, null);
		assertEquals(2, logic.count);
		assertEquals(1, player.extraRoll);
	}
	
	@Test
	public void testUseUp() {
		Card card = new Card();
		SmokeCloudLogic logic = new SmokeCloudLogic();
		card.logic = logic;
		card.name = "Smoke Cloud";
		logic.count = 1;
		Player player = new Player("test");
		player.addToHand(card);
		logic.use(player, null, null);
		assertEquals(0, logic.count);
		assertEquals(1, player.extraRoll);
		assertFalse(player.haveCard("Smoke Cloud"));
	}

}
