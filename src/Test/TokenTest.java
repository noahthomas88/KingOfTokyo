package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Game.Token;

class TokenTest {

	@Test
	void GenericTokenTest() {
		Token mytoken = new Token();
		assertEquals(mytoken.type(),"null");
	}

}
