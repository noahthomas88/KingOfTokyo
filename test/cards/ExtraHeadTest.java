package cards;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.ExtraHead;

public class ExtraHeadTest {

	@Test
	public void constructorTest() {
		ExtraHead extraHead = new ExtraHead();
		assertTrue(extraHead.name.equals("Extra Head"));
		assertTrue(extraHead.description.equals("Gain 1 extra die"));
		assertTrue(extraHead.type.equals("Keep"));
		assertTrue(extraHead.cost == 7);
		assertTrue(extraHead.logic.getClass().equals(ExtraHeadLogic.class));

	}

}
