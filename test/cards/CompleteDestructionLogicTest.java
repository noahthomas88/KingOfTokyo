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

public class CompleteDestructionLogicTest {

	@Test
	public void GameplayDiceRolledHaveCard() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		for(int i = 0; i<6; i++) {
			dice.add(EasyMock.niceMock(Dice.class));
			dice.get(i).numberRolled = i+1;
		}
		Player player = new Player("test");
		Card card = new Card();
		card.name = "Complete Destruction";
		player.addToHand(card);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("checkWin").createNiceMock();
		game.gameUI = ui;
		game.currentplayer = player;
		game.gameboard = board;
		
		EasyMock.expect(ui.numberToString(1)).andReturn("1");
		EasyMock.expect(ui.numberToString(2)).andReturn("2");
		EasyMock.expect(ui.numberToString(3)).andReturn("3");
		EasyMock.expect(ui.numberToString(4)).andReturn("attack");
		EasyMock.expect(ui.numberToString(5)).andReturn("energy");
		EasyMock.expect(ui.numberToString(6)).andReturn("heal");
		
		EasyMock.replay(ui);
		
		Messages message = new Messages("en");
		game.diceRolled(dice, message);

		assertEquals(9, player.victoryPoints);
	}

}
