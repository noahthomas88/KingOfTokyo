package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.MimicToken;
import Game.PoisonToken;
import Game.ShrinkToken;
import Game.SmokeToken;
import Game.Token;

public class TokenTest {

	@Test
	public void GenericTokenTypeTest() {
		Token mytoken = new Token();
		assertEquals(mytoken.getType(),null);
	}

	@Test
	public void PoisonTokenTypeTest() {
		Token mytoken = new PoisonToken();
		assertEquals(mytoken.getType(),"poison");
	}
	
	@Test
	public void MimicTokenTypeTest() {
		Token mytoken = new MimicToken();
		assertEquals(mytoken.getType(),"mimic");
	}
	
	@Test
	public void SmokeTokenTypeTest() {
		Token mytoken = new SmokeToken();
		assertEquals(mytoken.getType(),"smoke");
	}
	
	@Test
	public void ShrinkTokenTypeTest() {
		Token mytoken = new ShrinkToken();
		assertEquals(mytoken.getType(),"shrink");
	}
	
	
	
}
