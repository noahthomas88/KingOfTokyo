package CardTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Cards.ExtraHead;

public class ExtraHeadTest {

	@Test
	public void test() {
		ExtraHead extraHead = new ExtraHead();
		assertTrue(extraHead.name.equals("Extra Head"));
		assertTrue(extraHead.description.equals("Gain one extra die"));
		assertTrue(extraHead.type.equals("Keep"));
		assertTrue(extraHead.cost == 7);
	}

}
