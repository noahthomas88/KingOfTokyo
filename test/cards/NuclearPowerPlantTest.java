package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class NuclearPowerPlantTest {

	@Test
	public void constructorTest() {
		NuclearPowerPlant powerPlant = new NuclearPowerPlant();
		assertTrue(powerPlant.name.equals("Nuclear Power Plant"));
		assertTrue(powerPlant.description.equals("Gain 2 Victory Points and gain 3 Health"));
		assertTrue(powerPlant.type.equals("Discard"));
		assertTrue(powerPlant.cost == 6);
		assertTrue(powerPlant.logic.getClass().equals(NuclearPowerPlantLogic.class));

	}

}