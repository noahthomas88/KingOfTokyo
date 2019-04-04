package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class TankTest {

	@Test
	public void constructorTest() {
		Tank Tank = new Tank();
		assertTrue(Tank.name.equals("Tank"));
		assertTrue(Tank.description.equals("Gain 4 Victory Points and lose 3 Health"));
		assertTrue(Tank.type.equals("Discard"));
		assertTrue(Tank.cost == 4);	
		}

}
