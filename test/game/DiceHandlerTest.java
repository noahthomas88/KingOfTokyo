package game;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import game.DiceHandler;
import game.Player;

public class DiceHandlerTest {

	@Test
	public void constructorTest() {
		Player p = new Player("test");
		DiceHandler dh = new DiceHandler(p);
		assertFalse(dh.equals(null));
	}
	
	@Test
	public void numberOfDieTest() {
		Player p = new Player("test");
		DiceHandler dh = new DiceHandler(p);
		assertTrue(dh.numberOfDie() == 6);
	}
	
	

}
