package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlameThrowerTest {

	@Test
	public void test() {
		Flamethrower flamethrower = new Flamethrower();
		assertTrue(flamethrower.name.equals("Flamethrower"));
		assertTrue(flamethrower.description.equals("All other players lose 2 health"));
		assertTrue(flamethrower.type.equals("Discard"));
		assertTrue(flamethrower.cost == 3);
	}

}
