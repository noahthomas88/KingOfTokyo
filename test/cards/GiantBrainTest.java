package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class GiantBrainTest {

	@Test
	public void constructorTest() {
		GiantBrain bigBrain = new GiantBrain();
		assertTrue(bigBrain.name.equals("Giant Brain"));
		assertTrue(bigBrain.description.equals("You have one extra die roll each turn"));
		assertTrue(bigBrain.type.equals(""));
		assertTrue(bigBrain.cost == 5);
	}

}
