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

public class FreezeTimeLogicTest {

	@Test
	public void RolledThreeOnesGetAnotherTurnTest() {
		Player player = new Player("test");
		player.victoryPoints = 0;
		Card card = new Card();
		card.name = "Freeze Time";
		player.addToHand(card);

		ArrayList<Dice> dice = new ArrayList<Dice>();
		Messages message = EasyMock.niceMock(Messages.class);
		Board board = new Board(2, null);
		GUI gui = EasyMock.niceMock(GUI.class);
		Gameplay gameplay = new Gameplay(gui, player, board, null, null);

		Dice oneDie = new Dice(gameplay.currentplayer);
		oneDie.numberRolled = 1;
		dice.add(oneDie);
		dice.add(oneDie);
		dice.add(oneDie);
		dice.add(oneDie);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		board.playerList = players;
		board.cityPlayer = player;

		gameplay.diceRolled(dice, message);

		assertEquals(0, player.victoryPoints = 3);		
	}

}
