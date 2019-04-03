package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckConstructorTest {

	@Test
	public void testGetDeck() {
		DeckConstructor d = new DeckConstructor();
		
		assertEquals(d.createDeck(),null);

		
	}

}
