package cards;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
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
	
	@Test
	public void testAddDiscardToDeckEmptyToEmpty() {
		DeckConstructor d = new DeckConstructor();
		d.addDiscardtoDeck();
		assertTrue(d.deck.isEmpty());
		assertTrue(d.discard.isEmpty());
	}
	
	@Test
	public void testAddDiscardToDeckOneToEmpty() {
		DeckConstructor d = new DeckConstructor();
		Card card = EasyMock.niceMock(Card.class);
		d.discard.add(card);
		d.addDiscardtoDeck();
		assertEquals(1, d.deck.size());
		assertTrue(d.discard.isEmpty());
	}
	
	@Test
	public void testAddDiscardToDeckMultiToEmpty() {
		DeckConstructor d = new DeckConstructor();
		Card card = EasyMock.niceMock(Card.class);
		d.discard.add(card);
		d.discard.add(card);
		d.addDiscardtoDeck();
		assertEquals(2, d.deck.size());
		assertTrue(d.discard.isEmpty());
	}
	
	@Test
	public void testAddDiscardToDeckMultiToOne() {
		DeckConstructor d = new DeckConstructor();
		Card card = EasyMock.niceMock(Card.class);
		d.deck.add(card);
		d.discard.add(card);
		d.discard.add(card);
		d.addDiscardtoDeck();
		assertEquals(3, d.deck.size());
		assertTrue(d.discard.isEmpty());
	}
	
	@Test
	public void testReveal() {
		DeckConstructor d = new DeckConstructor();
		d.createDeck();
		d.shuffle();
		d.reveal();
		
		for (int index = 0; index < 3; index++) {
			assertNotEquals(d.visibleCard[index].name, "default");
		}
	}

}
