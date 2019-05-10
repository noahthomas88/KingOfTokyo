package cards;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.Player;

public class FlamethrowerLogicTest {
	
	@Test
	public void FlamethrowerUseTest2() {
		Player p = new Player("NewPlayer");
		Player p2 = new Player("NewPlayer2");
		Player p3 = new Player("NewPlayer3");
		
		p2.health = 5;
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		
		FlamethrowerLogic evactuationLogic = new FlamethrowerLogic();
		evactuationLogic.use(p, players, null);
		
		assertTrue(p.health == 10);
		assertTrue(p2.health == 3);
		assertTrue(p3.health == 8);
			
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUse() {
		FlamethrowerLogic logic = new FlamethrowerLogic();
		logic.use(null);
	}

}
