package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import Game.Board;

public class BoardTest {

	@Test
	public void testGetNumOfPlayersmin() {
		Board b = new Board(2);
		int num = b.getNumOfPlayers();

		assertEquals(2, num);
	}

	@Test
	public void testGetNumOfPlayersmax() {
		Board b = new Board(6);
		int num = b.getNumOfPlayers();

		assertEquals(6, num);
	}

	@Test
	public void testGetNumOfPlayersLB() {
		try{
			Board b = new Board(1);
			fail("should throw IllegalArgumentException");
		}catch(IllegalArgumentException e){
			
		}
	}

}
