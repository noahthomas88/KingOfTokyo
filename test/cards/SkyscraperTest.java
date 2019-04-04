package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class SkyscraperTest {

	@Test
	public void test() {
		Skyscraper skyscraper = new Skyscraper();
		assertTrue(skyscraper.name.equals("Skyscraper"));
		assertTrue(skyscraper.description.equals("Gain 4 Victory Points"));
		assertTrue(skyscraper.type.equals("Discard"));
		assertTrue(skyscraper.cost == 6);
	}

}
