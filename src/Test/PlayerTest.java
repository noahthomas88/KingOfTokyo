package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Player;

public class PlayerTest {

	@Test
	public void test() {
		Player player = new Player();
		assertTrue(player.isEmpty());
	}

}
