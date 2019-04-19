package cards;

import static org.junit.Assert.*;


import org.junit.Test;

import cards.Card;

public class CardTest {

	@Test
	public void testGetType1() {
		Card mycard = new Card();
		assertTrue(mycard.type.equals("base"));
	}
	
	@Test
	public void testGetCost() {
		Card mycard = new Card();
		assertTrue(mycard.getCost() == 0);
	}
	
	@Test
	public void testGetDescription() {
		Card mycard = new Card();
		assertTrue(mycard.description.equals("This is the base card, override me"));
	}
	
	@Test
	public void testGetName() {
		Card mycard = new Card();	
		assertTrue(mycard.getName().equals("default"));
	}
	
	@Test
	public void testgetCardLogic() {
		Card mycard = new Card();
		CardLogic logic = new CommuterTrainLogic();
		mycard.logic = logic;
		assertEquals(mycard.getCardLogic(),logic);
	}
	
	

}
