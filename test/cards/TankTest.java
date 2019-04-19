package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class TankTest {

	@Test
	public void constructorTest() {
		Tank tank = new Tank();
		assertTrue(tank.name.equals("Tank"));
		assertTrue(tank.description.equals("Gain 4 Victory Points and lose 3 Health"));
		assertTrue(tank.type.equals("Discard"));
		assertTrue(tank.cost == 4);	
		assertTrue(tank.logic.getClass().equals(TankLogic.class));

	}

}
