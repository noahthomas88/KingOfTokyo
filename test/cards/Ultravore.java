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

public class Ultravore {

	@Test
	public void testBeginTurn() {
		GUI gui = EasyMock.niceMock(GUI.class);
		Board board = EasyMock.niceMock(Board.class);
		Player player = new Player("test");
		Gameplay game = new Gameplay(gui, player, board, null, null);
		game.currentplayer = player;
		board.cityPlayer = player;
		
		Card ultravore = new Card();
		ultravore.name = "Ultravore";
		player.addToHand(ultravore);
		game.beginTurn();
		assertEquals(3, player.victoryPoints);
	}
	
	@Test
	public void testDiceRoll() {
		Board board = EasyMock.strictMock(Board.class);
		Player player = new Player("test");
		GUI ui = EasyMock.strictMock(GUI.class);
		Dice die = EasyMock.strictMock(Dice.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		dice.add(die);
		
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("calculateScore").addMockedMethod("checkWin").createStrictMock();
		
		board.doAttack(player, -2);
		ui.EnableCedeButton();
		game.calculateScore(new ArrayList<Dice>());
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);
		ui.DisableRollButton();
		game.checkWin();

		EasyMock.replay(board, ui, die, game);
		Messages message = new Messages("en");
		player.health = 5;
		player.energy = 5;
		
		Card ultravore = new Card();
		ultravore.name = "Ultravore";
		player.addToHand(ultravore);
		
		die.numberRolled = 4;
		game.gameUI = ui;
		game.gameboard = board;
		game.currentplayer = player;
		board.cityPlayer = player;
		
		game.diceRolled(dice, message);
		
		EasyMock.verify(board, ui, die, game);
		assertEquals(5, player.health);
		assertEquals(5, player.energy);
	}

}
