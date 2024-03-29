package cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.Player;

public class HighAltitudeBombingLogicTest {
	
	@Test
	public void HighAltitudeUseTest2() {
		Player p = new Player("NewPlayer");
		Player p2 = new Player("NewPlayer2");
		Player p3 = new Player("NewPlayer3");
		
		p.health = 5;
		p2.health = 7;
		p3.health = 9;
		
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		
		HighAltitudeBombingLogic bombingLogic = new HighAltitudeBombingLogic();
		bombingLogic.use(p, players, null);
		
		assertEquals(2, p.health);
		assertEquals(4, p2.health);
		assertEquals(6, p3.health);			
	}
}
