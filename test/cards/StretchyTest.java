package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class StretchyTest {

	@Test
	public void test() {
		assertTrue(stretchy.name.equals("stretchy"));
		assertTrue(stretchy.description.equals("Gain 4 Victory Points"));
		assertTrue(stretchy.type.equals("Discard"));
		assertTrue(stretchy.cost == 6);
	}

}
