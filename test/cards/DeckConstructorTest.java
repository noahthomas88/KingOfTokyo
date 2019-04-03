package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckConstructorTest {
	
	@Test
	public void testConstrctor() {
		DeckConstructor d = new DeckConstructor();
		assertTrue(d.deck.isEmpty());
		assertEquals(d.visibleCard.length, 3);
		for (int index = 0; index < 3; index++) {
			assertEquals(d.visibleCard[index].name, "default");
		}
		assertTrue(d.discard.isEmpty());
	}
	
	@Test
	public void testCreateDeck() {
		DeckConstructor d = new DeckConstructor();
		d.createDeck();
		assertFalse(d.deck.isEmpty());
		
		String[] cardNames = {"Apartment Building", "Commuter Train", "Energize", "Extra Head",
				"Gas Refinery", "Giant Brain", "High Altitude Bombing",
				"Jet Fighter", "National Guard", "Nuclear Power Plant", "Tank", "Ultravore"};
		for (int index = 0; index < cardNames.length; index++) {
			assertEquals(d.deck.get(index).name, cardNames[index]);
		}
	}

}
