package cards;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import game.Player;

public class VastStormLogicTest {
	
	@Test
	public void VastStormUseTest() {
		Player p = new Player("NewPlayer");
		Player p2 = new Player("NewPlayer2");
		Player p3 = new Player("NewPlayer3");
		Player p4 = new Player("NewPlayer4");
		
		p.energy = 0;
		p2.energy = 1;
		p3.energy = 4;
		p4.energy = 5;
		
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		
		VastStormLogic stormLogic = new VastStormLogic();
		stormLogic.use(p, players);
		
		assertTrue(p.energy == 2);
		assertTrue(p2.energy == 1);
		assertTrue(p3.energy == 2);
		assertTrue(p4.energy == 3);
			
	}

}
