package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class JetFighterLogicTest {

	@Test
	public void test() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		JetFighterLogic cardLogic = new JetFighterLogic();
		int savedVictoryPoints = p.victoryPoints;
		int savedHealth = p.health;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == savedVictoryPoints + 5);
		assertTrue(p.health == savedHealth - 4);
	}

}
