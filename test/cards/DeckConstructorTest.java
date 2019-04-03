package cards;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DeckConstructorTest {
	
	@Test
	public void testConstrctor() {
		DeckConstructor d = new DeckConstructor();
		assertTrue(d.deck.isEmpty());
	}
	
	@Test
	public void testCreateDeck() {
		DeckConstructor d = new DeckConstructor();
		d.createDeck();
		assertFalse(d.deck.isEmpty());
		assertEquals(d.deck.get(0).name, "Apartment Building");
		assertEquals(d.deck.get(1).name, "Extra Head");
		assertEquals(d.deck.get(2).name, "Gas Refinery");
		assertEquals(d.deck.get(3).name, "Giant Brain");
		assertEquals(d.deck.get(4).name, "High Altitude Bombing");
		assertEquals(d.deck.get(5).name, "Jet Fighter");
		assertEquals(d.deck.get(6).name, "Tank");
		assertEquals(d.deck.get(7).name, "Ultravore");
	}

}
