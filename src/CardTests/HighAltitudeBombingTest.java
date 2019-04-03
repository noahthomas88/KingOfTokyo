package CardTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Cards.HighAltitudeBombing;
import Cards.HighAltitudeBombing;

public class HighAltitudeBombingTest {

	@Test
	public void constructorTest() {
		HighAltitudeBombing HighAltitudeBombing = new HighAltitudeBombing();
		assertTrue(HighAltitudeBombing.name.equals("High Altitude Bombing"));
		assertTrue(HighAltitudeBombing.description.equals("All monsters including you lose 3 health"));
		assertTrue(HighAltitudeBombing.type.equals("Discard"));
		assertTrue(HighAltitudeBombing.cost == 4);
	}
}
