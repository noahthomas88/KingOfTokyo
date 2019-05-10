package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class GiantBrainLogicTest {

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
	public void testHaveCard() {
		Player player = new Player("test");
		Card card = new Card();
		card.name = "Giant Brain";
		player.addToHand(card);
		assertEquals(player.getNumberOfRolls(), 4);
	}

}
