package game;

import static org.junit.Assert.*;

import org.junit.Test;

import tokens.MimicToken;
import tokens.PoisonToken;
import tokens.ShrinkToken;
import tokens.SmokeToken;
import tokens.Token;

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
