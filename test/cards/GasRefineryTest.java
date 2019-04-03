package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.GasRefinery;

public class GasRefineryTest {
	
	@Test
	public void constructorTest() {
		GasRefinery GasRefinery = new GasRefinery();
		assertTrue(GasRefinery.name.equals("Gas Refinery"));
		assertTrue(GasRefinery.description.equals("Gain 2 Victory Points and all other monsters lose 3 health"));
		assertTrue(GasRefinery.type.equals("Discard"));
		assertTrue(GasRefinery.cost == 6);
	}

}
