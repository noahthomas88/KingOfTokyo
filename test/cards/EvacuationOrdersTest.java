package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class EvacuationOrdersTest {

	@Test
	public void constructorTest() {
		EvacuationOrders evac = new EvacuationOrders();
		assertTrue(evac.name.equals("Evacuation Orders"));
		assertTrue(evac.description.equals("All other monsters lose "
				+ "5 Victory Points"));
		assertTrue(evac.type.equals("Discard"));
		assertTrue(evac.cost == 7);
	}

}
