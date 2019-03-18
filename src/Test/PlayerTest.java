package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Player;

public class PlayerTest {

	@Test
	public void test() {
		Player player = new Player();
		assertFalse(player.isEmpty());
		assertTrue(player.health == 10);
		assertFalse(player.health == 9);
		assertFalse(player.health == 11);
	}

}
