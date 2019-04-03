package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.JetFighter;

public class JetFighterTest {

	@Test
	public void constructorTest() {
		JetFighter JetFighter  = new JetFighter();
		assertTrue(JetFighter.name.equals("Jet Fighter"));
		assertTrue(JetFighter.description.equals("Gain 5 Victory Points and lose 4 health"));
		assertTrue(JetFighter.type.equals("Discard"));
		assertTrue(JetFighter.cost == 5);
	}
}
