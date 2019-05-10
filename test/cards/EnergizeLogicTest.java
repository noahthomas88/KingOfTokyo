package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class EnergizeLogicTest {

	@Test
	public void energizeUseTest() {
		Player p = new Player("PlayerName");
		p.energy = 10;
		EnergizeLogic cardLogic = new EnergizeLogic();
		int saved = p.energy;
		cardLogic.use(p);
		assertTrue(p.energy == saved + 9);
		
	}
	
	@Test
	public void energizeUseTest2() {
		Player p = new Player("PlayerName");
		p.energy = 10;
		EnergizeLogic cardLogic = new EnergizeLogic();
		int saved = p.energy;
		cardLogic.use(p, null, null);
		assertTrue(p.energy == saved + 9);
		
	}

}
