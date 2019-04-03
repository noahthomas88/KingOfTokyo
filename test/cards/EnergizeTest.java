package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnergizeTest {

	@Test
	public void constructorTest() {
		Energize Energize  = new Energize();
		assertTrue(Energize.name.equals("Energize"));
		assertTrue(Energize.description.equals("Gain 9 energy"));
		assertTrue(Energize.type.equals("Discard"));
		assertTrue(Energize.cost == 8);
	}

}
