package Test;

import static org.junit.Assert.*;


import org.junit.Test;

import Game.Card;

public class CardTest {

	@Test
	public void testGetTypeNull() {
		Card mycard = new Card();
		assertTrue(mycard.getType()==null);
	}

}
