package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class HealTest {

	@Test
	public void constructorTest() {
		Heal heal = new Heal();
		assertTrue(heal.name.equals("Heal"));
		assertTrue(heal.description.equals("Gain 2 Health"));
		assertTrue(heal.type.equals("Discard"));
		assertTrue(heal.cost == 3);
	}

}
