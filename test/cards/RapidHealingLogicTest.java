package cards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.Player;

public class RapidHealingLogicTest {

	@Test
	public void testUse() {
		CardLogic logic = new RapidHealingLogic();
		Player player = new Player("test");
		player.energy = 2;
		player.health = 5;
		logic.use(player, null, null);
		assertEquals(6, player.health);
		assertEquals(0, player.energy);
	}
	
	@Test
	public void testCannotUse() {
		CardLogic logic = new RapidHealingLogic();
		Player player = new Player("test");
		player.energy = 1;
		player.health = 5;
		logic.use(player, null, null);
		assertEquals(5, player.health);
		assertEquals(1, player.energy);
	}

}
