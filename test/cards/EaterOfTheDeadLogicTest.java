package cards;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Gameplay;
import game.Player;
import main.GUI;

public class EaterOfTheDeadLogicTest {
	
	@Test
	public void eaterOfTheDeadLogicTest() {
		Player player = new Player("test");
		Player player2 = new Player("test2");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		card.name = "Eater of the Dead";
		
		GUI ui = EasyMock.niceMock(GUI.class);
		Gameplay game = EasyMock.niceMock(Gameplay.class);
		ui.game = game;
		player.ui = ui;
		player2.ui = ui;
		
		board.numOfPlayers = 2;
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player);
		playerList.add(player2);
		player.playerList = playerList;
		player2.playerList = playerList;
		
		player.addToHand(card);
		
		player2.playerDeath();
		
		assertEquals(player.victoryPoints, 3);
	}

}
