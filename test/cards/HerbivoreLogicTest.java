package cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Gameplay;
import game.Player;
import main.GUI;

public class HerbivoreLogicTest {
	
	@Test
	public void HerbivoreAspectTest() {
		Player player = new Player("test");
		Player player2 = new Player("victim");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		
		HashMap<String, Integer> playerToNumber = new HashMap<>();
		
		Gameplay game = new Gameplay(gui, player, board, null, playerToNumber);

		card.name = "Herbivore";
		
		board.numOfPlayers = 2;
		board.cityPlayer = player2;
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player);
		playerList.add(player2);
		player.playerList = playerList;
		player2.playerList = playerList;
		board.playerList = playerList;
		playerToNumber.put("test", 1);
		playerToNumber.put("victim", 2);
		player.addToHand(card);
		
		game.currentplayer = player;
		game.endTurn();

		assertEquals(1, player.victoryPoints);
	}
	
	@Test
	public void HerbivoreHurtTest() {
		Player player = new Player("test");
		Player player2 = new Player("victim");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		
		HashMap<String, Integer> playerToNumber = new HashMap<>();
		
		Gameplay game = new Gameplay(gui, player, board, null, playerToNumber);

		card.name = "Herbivore";
		
		board.numOfPlayers = 2;
		board.cityPlayer = player2;
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player);
		playerList.add(player2);
		player.playerList = playerList;
		player2.playerList = playerList;
		board.playerList = playerList;
		playerToNumber.put("test", 1);
		playerToNumber.put("victim", 2);
		player.addToHand(card);
		
		game.currentplayer = player;
		player2.addHealth(-1);
		game.endTurn();

		assertEquals(0, player.victoryPoints);
	}

}
