package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class ApartmentBuildingLogicTest {

	@Test
	public void apartmentBuildingUseTest() {
		Player p = new Player("TestPlayer");
		p.victoryPoints = 10;
		ApartmentBuildingLogic cardLogic = new ApartmentBuildingLogic();
		int saved = p.victoryPoints;
		cardLogic.use(p);
		assertTrue(p.victoryPoints == saved + 3);
	}

}
