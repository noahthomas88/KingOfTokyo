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

		assertEquals("test1", board.playerList.get(0).name);
		assertEquals("test2", board.playerList.get(1).name);
	}

	@Test
	public void testConstructPlayersIllegal() {
		Board board = new Board(2);

		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("");

		try {
			board.constructPlayers(names);
		} catch (IllegalArgumentException e) {

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

	@Test
	public void testDoAttackWithBothTokyoSpotsFull() {
		Board board = new Board(3);
		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("test2");
		names.add("test3");
		board.constructPlayers(names);

		Player tokyoPlayer = board.playerList.get(0);
		Player notTokyoPlayer = board.playerList.get(1);
		Player bayPlayer = board.playerList.get(2);
		int originalHealthP3 = bayPlayer.health;
		int originalHealthP2 = tokyoPlayer.health;
		int originalHealthP1 = notTokyoPlayer.health;
		board.cityPlayer = tokyoPlayer;
		board.bayPlayer = bayPlayer;

		for (int i = 0; i < 3; i++) {
			board.doAttack(tokyoPlayer);
		}
		assertTrue(notTokyoPlayer.health == originalHealthP1 - 3);
		assertTrue(tokyoPlayer.health == originalHealthP2);
		assertTrue(bayPlayer.health == originalHealthP3);

		for (int i = 0; i < 2; i++) {
			board.doAttack(notTokyoPlayer);
		}
		assertTrue(notTokyoPlayer.health == originalHealthP1 - 3);
		assertTrue(tokyoPlayer.health == originalHealthP2 - 2);
		assertTrue(bayPlayer.health == originalHealthP3 - 2);

		for (int i = 0; i < 1; i++) {
			board.doAttack(bayPlayer);
		}
		assertTrue(notTokyoPlayer.health == originalHealthP1 - 4);
		assertTrue(tokyoPlayer.health == originalHealthP2 - 2);
		assertTrue(bayPlayer.health == originalHealthP3 - 2);

	}
	
	@Test
	public void testDoAttackWithNoBayPlayer() {
		Board board = new Board(2);
		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("test2");
		board.constructPlayers(names);
		
		Player tokyoPlayer = board.playerList.get(0);
		Player notTokyoPlayer = board.playerList.get(1);
		int originalHealthP2 = tokyoPlayer.health;
		int originalHealthP1 = notTokyoPlayer.health;
		board.cityPlayer = tokyoPlayer;
		
		for(int i = 0; i < 3; i++) {
			board.doAttack(tokyoPlayer);
		}
		assertTrue(notTokyoPlayer.health == originalHealthP1 - 3);
		assertTrue(tokyoPlayer.health == originalHealthP2);
		
		for(int i = 0; i < 2; i++) {
			board.doAttack(notTokyoPlayer);
		}
		assertTrue(notTokyoPlayer.health == originalHealthP1 - 3);
		assertTrue(tokyoPlayer.health == originalHealthP2 - 2);
		
	}
	
	@Test
	public void testgetPlayerListSize() {
		Board board = new Board(2);
		ArrayList<Player> playerList = EasyMock.createMock(ArrayList.class);
		board.playerList = playerList;
		EasyMock.expect(playerList.size()).andReturn(2);
		EasyMock.replay(playerList);
		assertEquals(board.getPlayerListSize(), 2);
		EasyMock.verify(playerList);
	}
	

}
