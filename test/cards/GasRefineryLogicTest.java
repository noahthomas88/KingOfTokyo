package cards;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.Player;

public class GasRefineryLogicTest {
	
	@Test
	public void evactuationOrdersUseTest2() {
		Player p = new Player("NewPlayer");
		Player p2 = new Player("NewPlayer2");
		Player p3 = new Player("NewPlayer3");
		
		p.victoryPoints = 10;
		int psaved = p.victoryPoints;
		p2.health = 10;
		p3.health = 7;
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		
		EvacuationOrdersLogic evactuationLogic = new EvacuationOrdersLogic();
		evactuationLogic.use(p, players);
		
		assertTrue(p.victoryPoints == psaved + 2);
		assertTrue(p2.victoryPoints == 7);
		assertTrue(p3.victoryPoints == 4);
			
	}


}
