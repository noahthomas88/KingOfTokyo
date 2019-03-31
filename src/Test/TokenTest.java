package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Tokens.MimicToken;
import Tokens.PoisonToken;
import Tokens.ShrinkToken;
import Tokens.SmokeToken;
import Tokens.Token;

public class TokenTest {

	@Test
	public void GenericTokenTypeTest() {
		Token mytoken = new Token();
		assertEquals(mytoken.type,null);
	}

	@Test
	public void PoisonTokenTypeTest() {
		Token mytoken = new PoisonToken();
		assertEquals(mytoken.type,"poison");
	}
	
	@Test
	public void MimicTokenTypeTest() {
		Token mytoken = new MimicToken();
		assertEquals(mytoken.type,"mimic");
	}
	
	@Test
	public void SmokeTokenTypeTest() {
		Token mytoken = new SmokeToken();
		assertEquals(mytoken.type,"smoke");
	}
	
	@Test
	public void ShrinkTokenTypeTest() {
		Token mytoken = new ShrinkToken();
		assertEquals(mytoken.type,"shrink");
	}
	
	
}
