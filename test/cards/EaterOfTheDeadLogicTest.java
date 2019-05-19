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
	public void evenBiggerRemoveTest() {
		Player player = new Player("test");
		Player player2 = new Player("test2");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Eater of the Dead";
		
		board.numOfPlayers = 2;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		board.playerList.add(player2);
		
		player.addToHand(card);
		
		player2.playerDeath();
		
		assertEquals(player.victoryPoints, 3);
	}

}
