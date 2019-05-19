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

public class DetrivoreLogicTest {

	@Test
	public void addsTwoVictoryPointsTest() {
		Player player = new Player("test");
		Player attackedPlayer = new Player("test");
		attackedPlayer.victoryPoints = 0;
		Card card = EasyMock.strictMock(Card.class);
		card.name = "Acid Attack";
		Gameplay gameplay = EasyMock.mock(Gameplay.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		Messages message = EasyMock.mock(Messages.class);
		Board board = EasyMock.mock(Board.class);
		gameplay.gameboard = board;
		gameplay.currentplayer = player;
		
		gameplay.diceRolled(dice, message);
		
		assertEquals(2,player.victoryPoints);
	}

}
