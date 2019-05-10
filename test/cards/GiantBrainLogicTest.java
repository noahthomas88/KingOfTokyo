package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class GiantBrainLogicTest {
	
	@Test
	public void testHaveCard() {
		Player player = new Player("test");
		Card card = new Card();
		card.name = "Giant Brain";
		player.addToHand(card);
		assertEquals(player.getNumberOfRolls(), 4);
	}

}
