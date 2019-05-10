package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Dice;
import game.Player;

public class NovaBreathLogicTest {

	@Test
	public void testAttackEffectsAllPlayers() {
		Player player = new Player("test");
		Player player2 = new Player("test");
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player);
		playerList.add(player2);
		
		Card card = EasyMock.strictMock(Card.class);
		card.name = "NovaBreath";
		Board b = new Board(3);
		b.playerList = playerList;
		player2.health = 10;
		player.addToHand(card);
		b.doAttack(player, 1);
		assertEquals(player2.health, 9);
	}

}
