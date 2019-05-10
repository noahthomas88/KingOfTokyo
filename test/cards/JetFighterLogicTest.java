package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class JetFighterLogicTest {

	@Test
	public void jetFighterUseTest() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		JetFighterLogic cardLogic = new JetFighterLogic();
		int savedVictoryPoints = p.victoryPoints;
		int savedHealth = p.health;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == savedVictoryPoints + 5);
		assertTrue(p.health == savedHealth - 4);
	}
	
	@Test
	public void jetFighterUseTest2() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		JetFighterLogic cardLogic = new JetFighterLogic();
		int savedVictoryPoints = p.victoryPoints;
		int savedHealth = p.health;
		cardLogic.use(p, null, null);
		assertTrue(p.victoryPoints == savedVictoryPoints + 5);
		assertTrue(p.health == savedHealth - 4);
	}

}
