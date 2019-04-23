package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class GiantBrainLogicTest {

	@Test
	public void test() {
		Player p = new Player("PlayerName");
		assertTrue(p.numberOfDieRolls == 3);
		GiantBrainLogic cardLogic = new GiantBrainLogic();
		cardLogic.use(p);
		assertTrue(p.numberOfDieRolls == 4);
	}
	
	@Test
	public void test2() {
		Player p = new Player("PlayerName");
		assertTrue(p.numberOfDieRolls == 3);
		GiantBrainLogic cardLogic = new GiantBrainLogic();
		cardLogic.use(p, null);
		assertTrue(p.numberOfDieRolls == 4);
	}

}
