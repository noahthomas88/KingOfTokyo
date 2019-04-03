package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardLogicTest {

	@Test
	public void test() {
		CardLogic logic = new CardLogic();
		assertFalse(logic.use());
	}

}
