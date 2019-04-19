package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class HealLogicTest {

	@Test
	public void healUseTest() {
		Player p = new Player("TestPlayer");
		p.health = 8;
		HealLogic cardLogic = new HealLogic();
		int savedHealth = p.health;
		cardLogic.use(p);
		assertTrue(p.health == savedHealth + 2);
	}
	
	@Test
	public void healUseTest2() {
		Player p = new Player("TestPlayer");
		p.health = 8;
		HealLogic cardLogic = new HealLogic();
		int savedHealth = p.health;
		cardLogic.use(p, null);
		assertTrue(p.health == savedHealth + 2);
	}


}
