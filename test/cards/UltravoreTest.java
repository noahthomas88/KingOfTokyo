package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.Ultravore;

public class UltravoreTest {

	@Test
	public void constructorTest() {
		Ultravore Ultravore = new Ultravore();
		assertTrue(Ultravore.name.equals("Ultravore"));
		assertTrue(Ultravore.description.equals("Gain 1 extra victory point when beginning your turn in tokyo. If you roll an attack dice in tokyo add an attack to your roll"));
		assertTrue(Ultravore.type.equals("Keep"));
		assertTrue(Ultravore.cost == 4);
	}

}
