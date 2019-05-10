package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class CommuterTrainLogicTest {

	@Test
	public void commuterTrainUseTest() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		CommuterTrainLogic cardLogic = new CommuterTrainLogic();
		int saved = p.victoryPoints;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == saved + 2);
	}
	
	@Test
	public void commuterTrainUseTest2() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		CommuterTrainLogic cardLogic = new CommuterTrainLogic();
		int saved = p.victoryPoints;
		cardLogic.use(p, null, null);
		assertTrue(p.victoryPoints == saved + 2);
	}

}
