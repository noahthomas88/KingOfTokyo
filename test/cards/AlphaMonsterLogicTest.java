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

public class AlphaMonsterLogicTest {

	@Test
	public void GameplayDiceRolledTestAttack() {
		Board board = EasyMock.niceMock(Board.class);
		Player player = new Player("test");
		GUI ui = EasyMock.niceMock(GUI.class);
		Dice die = EasyMock.niceMock(Dice.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		dice.add(die);
		
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("calculateScore").addMockedMethod("checkWin").createNiceMock();

		EasyMock.replay(board, ui, die, game);
		Messages message = new Messages("en");
		
		Card card = new Card();
		card.name = "Alpha Monster";
		player.addToHand(card);
		
		player.victoryPoints = 5;
		die.numberRolled = 4;
		game.gameUI = ui;
		game.gameboard = board;
		game.currentplayer = player;
		game.diceRolled(dice, message);
		
		EasyMock.verify(board, ui, die, game);
		assertEquals(6, player.victoryPoints);
	}

}
