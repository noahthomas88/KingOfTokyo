package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class ExtraHeadLogicTest {

	@Test
	public void extraHeadUseTest() {
		Player p = new Player("NewPlayer");
		assertTrue(p.numberOfDieToRoll == 6);
		ExtraHeadLogic cardLogic = new ExtraHeadLogic();
		cardLogic.use(p);
		assertTrue(p.numberOfDieToRoll == 7);
		
	}
	
	@Test
	public void extraHeadUseTest2() {
		Player p = new Player("NewPlayer");
		assertTrue(p.numberOfDieToRoll == 6);
		ExtraHeadLogic cardLogic = new ExtraHeadLogic();
		cardLogic.use(p, null);
		assertTrue(p.numberOfDieToRoll == 7);
		
	}

}
