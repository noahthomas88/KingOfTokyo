package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class EnergizeLogicTest {

	@Test
	public void useTest() {
		Player p = new Player("PlayerName");
		p.energy = 10;
		EnergizeLogic cardLogic = new EnergizeLogic();
		int saved = p.victoryPoints;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == saved + 9);
		
	}

}
