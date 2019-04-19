package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class NationalGuardTest {

	@Test
	public void constructorTest() {
		NationalGuard nationalGuard  = new NationalGuard();
		assertTrue(nationalGuard.name.equals("National Guard"));
		assertTrue(nationalGuard.description.equals("Gain 2 Victory Points, But lose 2 Health"));
		assertTrue(nationalGuard.type.equals("Discard"));
		assertTrue(nationalGuard.cost == 3);
		assertTrue(nationalGuard.logic.getClass().equals(NationalGuardLogic.class));

	}
}
