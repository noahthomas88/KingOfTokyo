package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Dice;
import game.Gameplay;
import game.Player;
import main.Messages;

public class FireBreathingLogicTest {

	@Test
	public void NeighborsLoseOneHealthTest() {
		Player player = new Player("test");
		Player attackedPlayer = new Player("test");
		attackedPlayer.health = 2;
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Acid Attack";
		Gameplay gameplay = EasyMock.mock(Gameplay.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		Messages message = EasyMock.mock(Messages.class);
		Board board = EasyMock.mock(Board.class);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		players.add(attackedPlayer);
		board.playerList = players;
		board.cityPlayer = player;
		gameplay.gameboard = board;
		gameplay.currentplayer = player;

		gameplay.diceRolled(dice, message);

		assertEquals(1, attackedPlayer.health);
	}
}
