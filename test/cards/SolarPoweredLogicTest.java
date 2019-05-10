package cards;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Gameplay;
import game.Player;

public class SolarPoweredLogicTest {

	@Test
	public void testEndTurnNoEnergyHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Solar Powered";
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		player.energy = 0;
		player.addToHand(card);
		HashMap<String, Integer> playerToNumber= new HashMap<>();
		playerToNumber.put("test", 0);
		game.currentplayer = player;
		game.gameboard = board;
		game.playerToNumber = playerToNumber;
		
		System.out.println(game.currentplayer.name);
		game.endTurn();
		
		assertEquals(player.energy, 1);
	}

	@Test
	public void testEndTurnWithEnergyHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Solar Powered";
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		player.energy = 3;
		player.addToHand(card);
		HashMap<String, Integer> playerToNumber= new HashMap<>();
		playerToNumber.put("test", 0);
		game.currentplayer = player;
		game.gameboard = board;
		game.playerToNumber = playerToNumber;
		
		System.out.println(game.currentplayer.name);
		game.endTurn();
		
		assertEquals(player.energy, 3);
	}

}
