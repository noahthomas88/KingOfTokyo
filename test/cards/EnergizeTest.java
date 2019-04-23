package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnergizeTest {

	@Test
	public void constructorTest() {
		Energize energize  = new Energize();
		assertTrue(energize.name.equals("Energize"));
		assertTrue(energize.description.equals("Gain 9 energy"));
		assertTrue(energize.type.equals("Discard"));
		assertTrue(energize.cost == 8);
		assertTrue(energize.logic.getClass().equals(EnergizeLogic.class));

	}

}
