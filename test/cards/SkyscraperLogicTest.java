package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class SkyscraperLogicTest {

	@Test
	public void skyscraperUseTest() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		SkyscraperLogic cardLogic = new SkyscraperLogic();
		int saved = p.victoryPoints;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == saved + 4);
	}
	
	@Test
	public void skyscraperUseTest2() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		SkyscraperLogic cardLogic = new SkyscraperLogic();
		int saved = p.victoryPoints;
		cardLogic.use(p, null, null);
		assertTrue(p.victoryPoints == saved + 4);
	}

}
