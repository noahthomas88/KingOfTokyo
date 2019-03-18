package Test;

import static org.junit.Assert.*;

import Game.Token;

public class TokenTest {

	@org.junit.Test
	public void GenericTokenTest() {
		Token mytoken = new Token();
		assertEquals(mytoken.type(),"null");
	}

}
