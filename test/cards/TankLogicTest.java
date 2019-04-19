package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class TankLogicTest {

	@Test
	public void tankUseTest() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		TankLogic cardLogic = new TankLogic();
		int savedVictoryPoints = p.victoryPoints;
		int savedHealth = p.health;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == savedVictoryPoints + 4);
		assertTrue(p.health == savedHealth - 3);
	}

	@Test
	public void tankUseTest2() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		TankLogic cardLogic = new TankLogic();
		int savedVictoryPoints = p.victoryPoints;
		int savedHealth = p.health;
		cardLogic.use(p, null);
		assertTrue(p.victoryPoints == savedVictoryPoints + 4);
		assertTrue(p.health == savedHealth - 3);
	}
}
