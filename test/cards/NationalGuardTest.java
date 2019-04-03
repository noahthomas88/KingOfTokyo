package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class NationalGuardTest {

	@Test
	public void constructorTest() {
		NationalGuard NationalGuard  = new NationalGuard();
		assertTrue(NationalGuard.name.equals("National Guard"));
		assertTrue(NationalGuard.description.equals("+2 points, -2 health"));
		assertTrue(NationalGuard.type.equals("Discard"));
		assertTrue(NationalGuard.cost == 3);
	}
}
