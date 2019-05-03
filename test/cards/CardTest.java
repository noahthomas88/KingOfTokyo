package cards;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import cards.Card;

public class CardTest {

	@Test
	public void testGetType1() {
		Card mycard = new Card();
		assertTrue(mycard.type.equals("base"));
	}

}
