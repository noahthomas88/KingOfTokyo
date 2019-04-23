package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.JetFighter;

public class JetFighterTest {

	@Test
	public void constructorTest() {
		JetFighter jetFighter  = new JetFighter();
		assertTrue(jetFighter.name.equals("Jet Fighter"));
		assertTrue(jetFighter.description.equals("Gain 5 Victory Points and lose 4 health"));
		assertTrue(jetFighter.type.equals("Discard"));
		assertTrue(jetFighter.cost == 5);
		assertTrue(jetFighter.logic.getClass().equals(JetFighterLogic.class));

	}
}
