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

public class BurrowingLogicTest {

	@Test
	public void cedeTokyoTest() {
		Board board = EasyMock.strictMock(Board.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Player player = EasyMock.strictMock(Player.class);
		Player currentPlayer = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(ui, currentPlayer, board, null, null);
		
		EasyMock.expect(player.haveCard("Burrowing")).andReturn(true);
		currentPlayer.addHealth(-1);
		ui.moveToTokyo(currentPlayer);
		currentPlayer.addVictory(1);
		ui.updatePlayerText(board);
		ui.DisableCedeButton();

		EasyMock.replay(currentPlayer, player, ui);
		board.cityPlayer = player;
		gameplay.cedeTokyo();
		EasyMock.verify(currentPlayer, player, ui);

	}
	
	@Test
	public void cedeBayTest() {
		Board board = EasyMock.strictMock(Board.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Player player = EasyMock.strictMock(Player.class);
		Player currentPlayer = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(ui, currentPlayer, board, null, null);
		
		EasyMock.expect(player.haveCard("Burrowing")).andReturn(true);
		currentPlayer.addHealth(-1);
		ui.moveToBay(currentPlayer);
		currentPlayer.addVictory(1);
		ui.updatePlayerText(board);
		ui.DisableCedeButton();

		EasyMock.replay(currentPlayer, player, ui);
		board.bayPlayer = player;
		gameplay.cedeBay();
		EasyMock.verify(currentPlayer, player, ui);

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
		
		board.doAttack(player, -1);
		game.calculateScore(new ArrayList<Dice>());
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);
		ui.DisableRollButton();
		game.checkWin();

		EasyMock.replay(board, ui, die, game);
		Messages message = new Messages("en");
		
		Card ultravore = new Card();
		ultravore.name = "Burrowing";
		player.addToHand(ultravore);
		
		die.numberRolled = 5;
		game.gameUI = ui;
		game.gameboard = board;
		game.currentplayer = player;
		board.cityPlayer = player;
		
		game.diceRolled(dice, message);
		
		EasyMock.verify(board, ui, die, game);
	}
}
