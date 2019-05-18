package cards;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Gameplay;
import game.Player;
import main.GUI;

public class EnergyHoarderLogicTest {
	
	@Test
	public void testEndTurnSixEnergyHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Energy Hoarder";
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		player.energy = 6;
		player.victoryPoints = 0;
		player.addToHand(card);
		HashMap<String, Integer> playerToNumber= new HashMap<>();
		playerToNumber.put("test", 0);
		
		game.currentplayer = player;
		game.gameboard = board;
		game.playerToNumber = playerToNumber;
		game.gameUI = gui;
		
		game.endTurn();
		
		assertEquals(player.victoryPoints, 1);
	}

	@Test
	public void testEndTurnWithGreaterThanSixHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Energy Hoarder";
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		player.energy = 8;
		player.victoryPoints = 0;
		player.addToHand(card);
		HashMap<String, Integer> playerToNumber= new HashMap<>();
		playerToNumber.put("test", 0);
		
		game.currentplayer = player;
		game.gameboard = board;
		game.playerToNumber = playerToNumber;
		game.gameUI = gui;
		
		game.endTurn();
		
		assertEquals(player.victoryPoints, 1);
	}
	
	@Test
	public void testEndTurnWithZeroHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Energy Hoarder";
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		player.energy = 0;
		player.victoryPoints = 0;
		player.addToHand(card);
		HashMap<String, Integer> playerToNumber= new HashMap<>();
		playerToNumber.put("test", 0);
		
		game.currentplayer = player;
		game.gameboard = board;
		game.playerToNumber = playerToNumber;
		game.gameUI = gui;
		
		game.endTurn();
		
		assertEquals(player.victoryPoints, 0);
	}
	
	@Test
	public void testEndTurnWithTwelveHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Energy Hoarder";
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		player.energy = 12;
		player.victoryPoints = 0;
		player.addToHand(card);
		HashMap<String, Integer> playerToNumber= new HashMap<>();
		playerToNumber.put("test", 0);
		
		game.currentplayer = player;
		game.gameboard = board;
		game.playerToNumber = playerToNumber;
		game.gameUI = gui;
		
		game.endTurn();
		
		assertEquals(player.victoryPoints, 2);
	}

}
