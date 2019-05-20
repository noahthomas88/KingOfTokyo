package cards;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Gameplay;
import game.Player;
import main.GUI;

public class ItHasAChildLogicTest {
	
	@Test
	public void DeathWithCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);

		card.name = "It Has a Child";
		
		board.numOfPlayers = 2;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		player.addToHand(card);
		
		player.addHealth(-10);
		
		assertEquals(player.victoryPoints, 0);
		assertEquals(player.health, 10);
		assertEquals(player.cardsInHand.size(), 0);
	}

}
