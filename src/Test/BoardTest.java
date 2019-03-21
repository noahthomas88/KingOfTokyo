package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;
import Game.*;

public class BoardTest {

	@Test
	public void testNumOfPlayersmin() {
		Board b = new Board(2);

		assertEquals(2, b.numOfPlayers);
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

	@Test
	public void testConstructPlayers() {
		Board b = new Board(2);
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("test2");
		
		b.constructPlayers(names);

		assertEquals("test1", b.player.get(0).name);
		assertEquals("test2", b.player.get(1).name);
	}
	

}
