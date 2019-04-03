package game;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import cards.DeckConstructor;

public class BoardTest {

	@Test
	public void testNumOfPlayersmin() {
		Board board = new Board(2);

		assertEquals(2, board.numOfPlayers);
	}

	@Test
	public void testNumOfPlayersmax() {
		Board board = new Board(6);

		assertEquals(6, board.numOfPlayers);
	}

	@Test
	public void testNumOfPlayersLB() {
		try {
			Board board = new Board(1);
			fail("should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testNumOfPlayersUB() {
		try {
			Board board = new Board(7);
			fail("should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testConstructPlayers() {
		Board board = new Board(2);
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("test2");
		
		board.constructPlayers(names);

		assertEquals("test1", board.player.get(0).name);
		assertEquals("test2", board.player.get(1).name);
	}
	

	@Test
	public void testConstructPlayersIllegal() {
		Board board = new Board(2);
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("");
		
		try{
			board.constructPlayers(names);
		}catch(IllegalArgumentException e){
			
		}
	}

	@Test
	public void testInitializeDeck() {
		Board board = new Board(2);
		DeckConstructor deck = EasyMock.strictMock(DeckConstructor.class);
		board.deck = deck;
		
		deck.createDeck();
		deck.shuffle();
		EasyMock.replay(deck);
		
		board.initializeDeck();
		
		EasyMock.verify(deck);
	}
}
