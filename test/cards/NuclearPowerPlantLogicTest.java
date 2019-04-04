package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class NuclearPowerPlantLogicTest {

	@Test
	public void nuclearPowerPlantUseTest() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		NuclearPowerPlantLogic cardLogic = new NuclearPowerPlantLogic();
		int savedVictoryPoints = p.victoryPoints;
		int savedHealth = p.health;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == savedVictoryPoints + 2);
		assertTrue(p.health == savedHealth + 3);
	}

}
