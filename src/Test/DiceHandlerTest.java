package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.DiceHandler;

public class DiceHandlerTest {

	@Test
	public void constructorTest() {
		DiceHandler dh = new DiceHandler();
		assertFalse(dh.equals(null));
	}
	
	@Test
	public void numberOfDieTest() {
		DiceHandler dh = new DiceHandler();
		assertTrue(dh.numberOfDie() == 0);
	}
	
	

}
