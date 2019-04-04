package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class CornerStoreLogicTest {

	@Test
	public void test() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		CornerStoreLogic cardLogic = new CornerStoreLogic();
		int saved = p.victoryPoints;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == saved + 1);
	}

}
