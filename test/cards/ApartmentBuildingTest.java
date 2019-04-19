package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApartmentBuildingTest {

	@Test
	public void constructorTest() {
		ApartmentBuilding apartmentBuilding = new ApartmentBuilding();
		assertTrue(apartmentBuilding.name.equals("Apartment Building"));
		assertTrue(apartmentBuilding.description.equals("Gain 3 Victory Points"));
		assertTrue(apartmentBuilding.type.equals("Discard"));
		assertTrue(apartmentBuilding.cost == 5);
		assertTrue(apartmentBuilding.logic.getClass().equals(ApartmentBuildingLogic.class));
	}

}
