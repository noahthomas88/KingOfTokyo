package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Dice;
import game.Gameplay;
import game.Player;
import main.GUI;
import main.Messages;

public class DetrivoreLogicTest {

	@Test
	public void addsTwoVictoryPointsTest() {
		Player player = new Player("test");
		Card card = new Card();
		card.name = "Detrivore";
		Board board = new Board(2, null);
		GUI gui = EasyMock.niceMock(GUI.class);
		Gameplay gameplay = new Gameplay(gui,player,board,null,null);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		Messages message = EasyMock.niceMock(Messages.class);
		gameplay.gameboard = board;
		gameplay.currentplayer = player;
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		board.playerList  = players;
		board.cityPlayer = player;
		
		gameplay.diceRolled(dice, message);
		
		assertEquals(2,player.victoryPoints);
	}
}
