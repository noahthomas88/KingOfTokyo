package cards;

import org.junit.Test;

public class MakingStrongerLogicTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testUse() {
		MakingStrongerLogic logic = new MakingStrongerLogic();
		logic.use(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUse2() {
		MakingStrongerLogic logic = new MakingStrongerLogic();
		logic.use(null, null);
	}

}
