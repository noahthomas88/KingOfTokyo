package cards;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.Player;

public class EvacuationOrdersLogicTest {
	
	@Test
	public void evactuationOrdersUseTest2() {
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
		
		EvacuationOrdersLogic evactuationLogic = new EvacuationOrdersLogic();
		evactuationLogic.use(p, players);
		
		assertTrue(p.victoryPoints == 10);
		assertTrue(p2.victoryPoints == 5);
		assertTrue(p3.victoryPoints == 11);
			
	}

}
