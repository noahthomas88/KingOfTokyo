package game;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.Card;

public class CardTest {

	@Test
	public void testConstructor() {
		Card card = new Card();
		assertTrue(card.name.equals("default"));
		assertTrue(card.type.equals("base"));
		assertTrue(card.cost == 0);
		assertTrue(card.description.equals("This is the base card, override me"));
	}

}
