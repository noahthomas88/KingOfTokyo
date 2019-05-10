package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class NationalGuardLogicTest {

	@Test
	public void nationalGuardUseTest() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		NationalGuardLogic cardLogic = new NationalGuardLogic();
		int savedVictoryPoints = p.victoryPoints;
		int savedHealth = p.health;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == savedVictoryPoints + 2);
		assertTrue(p.health == savedHealth - 2);
	}
	
	@Test
	public void nationalGuardUseTest2() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		NationalGuardLogic cardLogic = new NationalGuardLogic();
		int savedVictoryPoints = p.victoryPoints;
		int savedHealth = p.health;
		cardLogic.use(p, null, null);
		assertTrue(p.victoryPoints == savedVictoryPoints + 2);
		assertTrue(p.health == savedHealth - 2);
	}

}
