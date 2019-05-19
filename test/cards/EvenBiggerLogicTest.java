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

public class EvenBiggerLogicTest {
	
	@Test
	public void evenBiggerUseTest() {
		Player p = new Player("TestPlayer");
		EvenBiggerLogic cardLogic = new EvenBiggerLogic();
		cardLogic.use(p);
		assertTrue(p.maxHealth == 12);
	}
	
	@Test
	public void evenBiggerUseTest2() {
		Player p = new Player("TestPlayer");
		EvenBiggerLogic cardLogic = new EvenBiggerLogic();
		cardLogic.use(p, null, null);
		assertTrue(p.maxHealth == 12);
	}
	
	@Test
	public void evenBiggerRemoveTest() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Even Bigger";
		card.logic = new EvenBiggerLogic();
		
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		assertEquals(player.maxHealth, 10);
		player.addToHand(card);
		card.logic.use(player, null, null);
		assertEquals(player.maxHealth, 12);
		
		player.removeCard(card.name);
		
		assertEquals(player.maxHealth,10);
	}

}
