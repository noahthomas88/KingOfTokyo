package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class RootingForTheUnderdogTest {

	@Test
	public void consturctorTest() {
		RootingForTheUnderdog rootingForTheUnderdog = new RootingForTheUnderdog();
		assertTrue(rootingForTheUnderdog.name.equals("Rooting for the Underdog"));
		assertTrue(rootingForTheUnderdog.description.equals("At the end of turn, if you have the fewest victory points, gain a victory point"));
		assertTrue(rootingForTheUnderdog.type.equals("Keep"));
		assertTrue(rootingForTheUnderdog.cost == 3);
		assertTrue(rootingForTheUnderdog.logic.getClass().equals(RootingForTheUnderdogLogic.class));
	}

}
