package cards;

import static org.junit.Assert.*;

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
		
		String[] cardNames = {"Apartment Building", "Energize", "Extra Head",
				"Gas Refinery", "Giant Brain", "High Altitude Bombing",
				"Jet Fighter", "National Guard", "Nuclear Power Plant", "Tank", "Ultravore"};
		for (int index = 0; index < cardNames.length; index++) {
			assertEquals(d.deck.get(index).name, cardNames[index]);
		}
	}

}
