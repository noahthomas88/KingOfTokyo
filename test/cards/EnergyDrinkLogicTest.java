package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Player;

public class EnergyDrinkLogicTest {

	@Test
	public void testNotEnoughEnergy() {
		Player player = new Player("test");
		CardLogic logic = new EnergyDrinkLogic();
		logic.use(player, null, null);
		assertEquals(0, player.energy);
		assertEquals(0, player.extraRoll);
	}
	
	@Test
	public void testEnoughEnergy() {
		Player player = new Player("test");
		CardLogic logic = new EnergyDrinkLogic();
		player.energy = 1;
		logic.use(player, null, null);
		assertEquals(0, player.energy);
		assertEquals(1, player.extraRoll);
	}

}
