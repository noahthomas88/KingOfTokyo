package cards;

import org.junit.Test;

public class ArmorPlatingLogicTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testUse() {
		ArmorPlatingLogic logic = new ArmorPlatingLogic();
		logic.use(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUse2() {
		ArmorPlatingLogic logic = new ArmorPlatingLogic();
		logic.use(null, null);
	}

}
