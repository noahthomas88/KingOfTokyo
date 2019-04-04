package cards;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

public class DeckConstructorTest {
	
	@Test
	public void testConstrctor() {
		DeckConstructor deckCons = new DeckConstructor();
		assertTrue(deckCons.deck.isEmpty());
		assertEquals(deckCons.visibleCard.length, 3);
		for (int index = 0; index < 3; index++) {
			assertEquals(deckCons.visibleCard[index].name, "default");
		}
		assertTrue(deckCons.discard.isEmpty());
	}
	
	@Test
	public void testCreateDeck() {
		DeckConstructor deckCons = new DeckConstructor();
		deckCons.createDeck();
		assertFalse(deckCons.deck.isEmpty());
		
		String[] cardNames = {"Apartment Building", "Commuter Train", "Corner Store", "Energize", 
				"Evacuation Orders", "Extra Head", "Gas Refinery", "Giant Brain", "Heal",
				"High Altitude Bombing", "Jet Fighter", "National Guard", "Nuclear Power Plant",
				"Skyscraper", "Tank", "Ultravore"};
		for (int index = 0; index < cardNames.length; index++) {
			assertEquals(deckCons.deck.get(index).name, cardNames[index]);
		}
	}
	
	@Test
	public void testAddDiscardToDeckEmptyToEmpty() {
		DeckConstructor deckCons = new DeckConstructor();
		deckCons.addDiscardtoDeck();
		assertTrue(deckCons.deck.isEmpty());
		assertTrue(deckCons.discard.isEmpty());
	}
	
	@Test
	public void testAddDiscardToDeckOneToEmpty() {
		DeckConstructor deckCons = new DeckConstructor();
		Card card = EasyMock.niceMock(Card.class);
		deckCons.discard.add(card);
		deckCons.addDiscardtoDeck();
		assertEquals(1, deckCons.deck.size());
		assertTrue(deckCons.discard.isEmpty());
	}
	
	@Test
	public void testAddDiscardToDeckMultiToEmpty() {
		DeckConstructor deckCons = new DeckConstructor();
		Card card = EasyMock.niceMock(Card.class);
		deckCons.discard.add(card);
		deckCons.discard.add(card);
		deckCons.addDiscardtoDeck();
		assertEquals(2, deckCons.deck.size());
		assertTrue(deckCons.discard.isEmpty());
	}
	
	@Test
	public void testAddDiscardToDeckMultiToOne() {
		DeckConstructor deckCons = new DeckConstructor();
		Card card = EasyMock.niceMock(Card.class);
		deckCons.deck.add(card);
		deckCons.discard.add(card);
		deckCons.discard.add(card);
		deckCons.addDiscardtoDeck();
		assertEquals(3, deckCons.deck.size());
		assertTrue(deckCons.discard.isEmpty());
	}
	
	@Test
	public void testRevealEmptyDeck() {
		DeckConstructor deckCons = EasyMock.createMockBuilder(DeckConstructor.class).addMockedMethod("shuffle").withConstructor().createNiceMock();
		Card card = EasyMock.createMockBuilder(Heal.class).withConstructor().createMock();
		
		deckCons.shuffle();
		EasyMock.replay(deckCons);
		
		deckCons.discard.add(card);
		deckCons.discard.add(card);
		deckCons.discard.add(card);
		
		deckCons.reveal();
		
		EasyMock.verify(deckCons);		
		assertTrue(deckCons.deck.isEmpty());
		assertTrue(deckCons.discard.isEmpty());
		for (int index = 0; index<3; index++) {
			assertNotEquals(deckCons.visibleCard[index].name, "default");
		}
	}
	
	@Test
	public void testRevealOneCardDeck() {
		DeckConstructor deckCons = new DeckConstructor();
		Card card = EasyMock.createMockBuilder(Heal.class).withConstructor().createMock();
			
		deckCons.discard.add(card);
		deckCons.discard.add(card);
		deckCons.discard.add(card);
		deckCons.deck.add(card);
		
		deckCons.reveal();
		
		assertTrue(deckCons.discard.isEmpty());
		assertEquals(deckCons.deck.size(), 1);
		for (int index = 0; index<3; index++) {
			assertNotEquals(deckCons.visibleCard[index].name, "default");
		}
	}
	
	@Test
	public void testRevealMultiCardDeck() {
		DeckConstructor deckCons = new DeckConstructor();
		Card card = EasyMock.createMockBuilder(Heal.class).withConstructor().createMock();
			
		deckCons.discard.add(card);
		deckCons.deck.add(card);
		deckCons.deck.add(card);
		deckCons.deck.add(card);
		
		deckCons.reveal();
		
		assertTrue(deckCons.deck.isEmpty());
		assertEquals(deckCons.discard.size(), 1);
		for (int index = 0; index<3; index++) {
			assertNotEquals(deckCons.visibleCard[index].name, "default");
		}
	}
	
	@Test
	public void testAddToEmptyDiscard(){
		DeckConstructor deckCons = new DeckConstructor();
		Card card = EasyMock.createMockBuilder(Heal.class).withConstructor().createMock();
		
		deckCons.addToDiscard(card);
		
		assertEquals(deckCons.discard.size(), 1);		
	}
	
	@Test
	public void testAddToNonEmptyDiscard(){
		DeckConstructor deckCons = new DeckConstructor();
		Card card = EasyMock.createMockBuilder(Heal.class).withConstructor().createMock();
		deckCons.discard.add(card);
		deckCons.addToDiscard(card);
		
		assertEquals(deckCons.discard.size(), 2);		
	}

}
