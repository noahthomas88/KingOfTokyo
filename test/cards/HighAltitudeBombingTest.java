package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.HighAltitudeBombing;
import cards.HighAltitudeBombing;

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
