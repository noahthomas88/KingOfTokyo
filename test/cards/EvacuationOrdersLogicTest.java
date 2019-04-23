package cards;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.Player;

public class EvacuationOrdersLogicTest {
	
	@Test
	public void evactuationOrdersUseTest() {
		Player p = new Player("NewPlayer");
		Player p2 = new Player("NewPlayer2");
		Player p3 = new Player("NewPlayer3");
		
		p.victoryPoints = 10;
		p2.victoryPoints = 10;
		p3.victoryPoints = 16;
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		
		evacuationLogic evactuationLogic = new evacuationLogic();
		evactuationLogic.use(p, players);
		
		assertTrue(p.victoryPoints == 10);
		assertTrue(p2.victoryPoints == 5);
		assertTrue(p3.victoryPoints == 11);
		
		
		
	}
	
	@Test
	public void evacuationOrdersUseTest2() {
		Player p = new Player("NewPlayer");
		assertTrue(p.numberOfDieToRoll == 6);
		ExtraHeadLogic cardLogic = new ExtraHeadLogic();
		cardLogic.use(p, null);
		assertTrue(p.numberOfDieToRoll == 7);
		
	}

}
