package cards;

import org.junit.Test;

public class RegenerationLogicTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testUse() {
		RegenerationLogic logic = new RegenerationLogic();
		logic.use(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUse2() {
		RegenerationLogic logic = new RegenerationLogic();
		logic.use(null, null);
	}

}
