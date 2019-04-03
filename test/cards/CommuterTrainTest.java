package cards;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommuterTrainTest {

	@Test
	public void constructorTest() {
		CommuterTrain iLikeTrains = new CommuterTrain();
		assertTrue(iLikeTrains.name.equals("Commuter Train"));
		assertTrue(iLikeTrains.description.equals("Gain 2 Victory Points"));
		assertTrue(iLikeTrains.type.equals("Discard"));
		assertTrue(iLikeTrains.cost == 4);
	}

}
