package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class ExtraHeadLogicTest {
	
	@Test
	public void testHaveCard() {
		Player player = new Player("test");
		Card card = new Card();
		card.name = "Extra Head";
		player.addToHand(card);
		assertEquals(player.getNumberOfDie(), 7);
	}

}
