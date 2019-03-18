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

}
