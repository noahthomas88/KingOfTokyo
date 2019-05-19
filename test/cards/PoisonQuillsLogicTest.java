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

public class PoisonQuillsLogicTest {

	@Test
	public void RolledThreeTwosAddTwoAttacksTest() {
		Player player = new Player("test");
		Player attackedPlayer = new Player("test");
		attackedPlayer.health = 2;
		player.victoryPoints = 0;
		Card card = new Card();
		card.name = "Poison Quills";
		player.addToHand(card);

		ArrayList<Dice> dice = new ArrayList<Dice>();
		Messages message = EasyMock.niceMock(Messages.class);
		Board board = new Board(2, null, null);
		GUI gui = EasyMock.niceMock(GUI.class);
		Gameplay gameplay = new Gameplay(gui, player, board, null, null);

		Dice twoDie = new Dice(gameplay.currentplayer);
		twoDie.numberRolled = 2;
		dice.add(twoDie);
		dice.add(twoDie);
		dice.add(twoDie);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		players.add(attackedPlayer);
		board.playerList = players;
		board.cityPlayer = player;

		gameplay.diceRolled(dice, message);

		assertEquals(0, attackedPlayer.health);
	}

}
