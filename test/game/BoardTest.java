package game;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import cards.DeckConstructor;
import main.GUI;

public class BoardTest {

	@Test
	public void testNumOfPlayersmin() {
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board board = new Board(2, null, mockedUI);

		assertEquals(2, board.numOfPlayers);
	}

	@Test
	public void testNumOfPlayersmax() {
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board board = new Board(6, null, mockedUI);

		assertEquals(6, board.numOfPlayers);
	}

	@Test
	public void testNumOfPlayersLB() {
		try {
			GUI mockedUI = EasyMock.createNiceMock(GUI.class);
			Board board = new Board(1, null, mockedUI);
			fail("should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testNumOfPlayersUB() {
		try {
			GUI mockedUI = EasyMock.createNiceMock(GUI.class);
			Board board = new Board(7, null, mockedUI);
			fail("should throw IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testConstructPlayers() {
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board board = new Board(2, null, mockedUI);

		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("test2");

		board.constructPlayers(names);

		assertEquals("test1", board.playerList.get(0).name);
		assertEquals("test2", board.playerList.get(1).name);
		assertEquals(board.playerList.get(0).playerList, board.playerList);
		assertEquals(board.playerList.get(1).playerList, board.playerList);
	}

	@Test
	public void testConstructPlayersIllegal() {
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board board = new Board(2, null, mockedUI);

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
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board board = new Board(2, null, mockedUI);
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
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board board = new Board(3, null, mockedUI);
		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("test2");
		names.add("test3");
		board.constructPlayers(names);

		Player cityPlayer = board.playerList.get(0);
		Player outPlayer = board.playerList.get(1);
		Player bayPlayer = board.playerList.get(2);
		int cityHealth = cityPlayer.health;
		int outHealth = outPlayer.health;
		int bayHealth = bayPlayer.health;
		
		
		board.cityPlayer = cityPlayer;
		board.bayPlayer = bayPlayer;

		board.doAttack(cityPlayer, -3);
		assertTrue(outPlayer.health == outHealth - 3);
		assertTrue(cityPlayer.health == cityHealth);
		assertTrue(bayPlayer.health == bayHealth);

		board.doAttack(outPlayer, -2);
		assertTrue(outPlayer.health == outHealth - 3);
		assertTrue(cityPlayer.health == cityHealth - 2);
		assertTrue(bayPlayer.health == bayHealth - 2);

		board.doAttack(bayPlayer, -1);
		assertTrue(outPlayer.health == outHealth - 4);
		assertTrue(cityPlayer.health == cityHealth - 2);
		assertTrue(bayPlayer.health == bayHealth - 2);

	}
	
	@Test
	public void testDoAttackWithNoBayPlayer() {
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board board = new Board(2, null, mockedUI);
		ArrayList<String> names = new ArrayList<>();
		names.add("test1");
		names.add("test2");
		board.constructPlayers(names);
		
		Player tokyoPlayer = board.playerList.get(0);
		Player notTokyoPlayer = board.playerList.get(1);
		int originalHealthP2 = tokyoPlayer.health;
		int originalHealthP1 = notTokyoPlayer.health;
		board.cityPlayer = tokyoPlayer;
		
		board.doAttack(tokyoPlayer, -3);
		assertTrue(notTokyoPlayer.health == originalHealthP1 - 3);
		assertTrue(tokyoPlayer.health == originalHealthP2);
		
		board.doAttack(notTokyoPlayer, -2);
		assertTrue(notTokyoPlayer.health == originalHealthP1 - 3);
		assertTrue(tokyoPlayer.health == originalHealthP2 - 2);
		
	}

}
