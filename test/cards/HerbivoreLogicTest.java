package cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
		Gameplay game = new Gameplay(gui, player, board, null, null);

		card.name = "Herbivore";
		
		board.numOfPlayers = 2;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		board.playerList.add(player2);
		
		player.addToHand(card);
		
		game.currentplayer = player;
		game.endTurn();

		assertEquals(player.victoryPoints, 1);
	}

}
