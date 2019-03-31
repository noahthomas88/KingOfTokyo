package Test;

import static org.junit.Assert.*;


import org.junit.Test;

import Cards.Card;

public class CardTest {

	@Test
	public void testGetType1() {
		Card mycard = new Card();
		assertTrue(mycard.type.equals("base"));
	}
	
	@Test
	public void testGetCost() {
		Card mycard = new Card();
		assertTrue(mycard.cost == 0);
	}
	
	@Test
	public void testGetDescription() {
		Card mycard = new Card();
		assertTrue(mycard.description.equals("This is the base card, override me"));
	}
	
	
	

}
