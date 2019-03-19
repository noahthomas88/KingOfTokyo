package Test;

import static org.junit.Assert.*;

import org.junit.Test;
import Game.*;

public class BoardTest {

	@Test
	public void testNumOfPlayersmin() {
		Board b = new Board(2);		

		assertEquals(2, b.numOfPlayers);
		for(int i = 0;i<2;i++){
			assertEquals(Player.class, b.player[i].getClass());
		}	
	}

	@Test
	public void testNumOfPlayersmax() {
		Board b = new Board(6);

		assertEquals(6, b.numOfPlayers);
	}

	@Test
	public void testNumOfPlayersLB() {
		try {
			Board b = new Board(1);
			fail("should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testNumOfPlayersUB() {
		try {
			Board b = new Board(7);
			fail("should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

}
