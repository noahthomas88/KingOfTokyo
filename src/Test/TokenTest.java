package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Token;

class TokenTest {

	@Test
	void GenericTokenTest() {
		Token mytoken = new Token();
		assertEquals(mytoken.type(),"null");
	}

}
