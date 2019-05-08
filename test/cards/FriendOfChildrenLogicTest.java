package cards;

import org.junit.Test;

public class FriendOfChildrenLogicTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testUse() {
		FriendOfChildrenLogic logic = new FriendOfChildrenLogic();
		logic.use(null);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUse2() {
		FriendOfChildrenLogic logic = new FriendOfChildrenLogic();
		logic.use(null, null);
	}

}
