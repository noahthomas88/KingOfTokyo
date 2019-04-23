package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class RootingForTheUnderdogTest {

	@Test
	public void consturctorTest() {
		RootingForTheUnderdog rootingForTheUnderdog = new RootingForTheUnderdog();
		assertTrue(rootingForTheUnderdog.name.equals("Nuclear Power Plant"));
		assertTrue(rootingForTheUnderdog.description.equals("Gain 2 Victory Points and gain 3 Health"));
		assertTrue(rootingForTheUnderdog.type.equals("Discard"));
		assertTrue(rootingForTheUnderdog.cost == 6);
		assertTrue(rootingForTheUnderdog.logic.getClass().equals(RootingForTheUnderdogLogic.class));
	}

}
