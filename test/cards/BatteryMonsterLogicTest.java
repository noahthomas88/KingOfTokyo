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

public class BatteryMonsterLogicTest {
	
	@Test
	public void BatteryMonsterUseTest() {
		Player p = new Player("TestPlayer");
		BatteryMonsterLogic cardLogic = new BatteryMonsterLogic();
		cardLogic.use(p);
		assertTrue(p.energyStore == 6);
	}
	
	@Test
	public void BatteryMonsterUseTest2() {
		Player p = new Player("TestPlayer");
		BatteryMonsterLogic cardLogic = new BatteryMonsterLogic();
		cardLogic.use(p, null, null);
		assertTrue(p.energyStore == 6);
	}
	
	@Test
	public void BatteryMonsterAspectTest() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = new Gameplay(gui, player, board, null, null);

		card.name = "Battery Monster";
		card.logic = new BatteryMonsterLogic();
		
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		player.addToHand(card);
		card.logic.use(player, null, gui);
		
		assertEquals(player.energyStore, 6);
		
		game.currentplayer = player;
		game.beginTurn();
		assertEquals(player.energyStore, 4);
		assertEquals(player.energy, 2);
		
		game.beginTurn();
		assertEquals(player.energyStore, 2);
		assertEquals(player.energy, 4);
		
		game.beginTurn();
		assertEquals(player.energyStore, 0);
		assertEquals(player.energy, 6);
		
		game.beginTurn();

		assertTrue(!player.haveCard("Battery Monster"));
		
	}

}
