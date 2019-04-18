package game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import cards.Card;
import cards.DeckConstructor;
import game.Gameplay;
import main.GUI;

public class GameplayTest {

	
	@Test
	public void endTurnTest() {
		Board board = new Board(2);
		Player firstPlayer = board.playerList.get(0);
		Player secondPlayer = board.playerList.get(0);
		
		Gameplay g = new Gameplay(null, null, null, null, null);

		g.currentplayer = firstPlayer;
		g.endTurn();
		assertFalse(g.currentplayer != firstPlayer);
		assertFalse(g.currentplayer == secondPlayer);
	}
	
	@Test
	public void GameplayConstructorTest() {
		Player currentplayer = new Player("null");
		Board gameboard = new Board(2);
		GUI gameUI = new GUI();
		DeckConstructor deck = new DeckConstructor();
		HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();
		Gameplay game = new Gameplay(gameUI, currentplayer, gameboard, deck, playerToNumber);
		assertEquals(game.gameUI, gameUI);
		assertEquals(game.gameboard, gameboard);
		assertEquals(game.playerToNumber, playerToNumber);
		assertEquals(game.deck, deck);
	}

	//mock 3 cards add them to the public feilds in a mocked deck construtor
	@Test
	public void buyCardUpdatesPlayerDisplayTextTest() {
		GUI gui = EasyMock.mock(GUI.class);
		Player currentPlayer = EasyMock.mock(Player.class);
		Board board = EasyMock.mock(Board.class);
		DeckConstructor deck = EasyMock.mock(DeckConstructor.class);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Card firstCard = EasyMock.mock(Card.class);
		Card secondCard = EasyMock.mock(Card.class);
		Card thirdCard = EasyMock.mock(Card.class);
		

		EasyMock.expect(currentPlayer.energy).andReturn(1);
		EasyMock.expect(firstCard.cost).andReturn(1);

		EasyMock.replay(currentPlayer);
		EasyMock.replay(firstCard);

		gui.updatePlayerText(board);
		EasyMock.replay(gui);

		Gameplay g = new Gameplay(gui, currentPlayer, board, deck, map);

		g.buyCard(1);

		EasyMock.verify(gui);
	}

	@Test
	public void buyCardFailTest() {
		GUI gui = EasyMock.mock(GUI.class);
		Player currentPlayer = EasyMock.mock(Player.class);
		Card firstCard = EasyMock.mock(Card.class);
		Card secondCard = EasyMock.mock(Card.class);
		Card thirdCard = EasyMock.mock(Card.class);

		
		EasyMock.expect(currentPlayer.energy).andReturn(0);
		EasyMock.expect(firstCard.cost).andReturn(1);

		EasyMock.replay(currentPlayer);
		EasyMock.replay(firstCard);

		gui.energyWarning();
		EasyMock.replay(gui);

		Gameplay g = new Gameplay(gui, currentPlayer, null, null, null);

		g.buyCard(1);

		EasyMock.verify(gui);
	}

	@Test
	public void buyCardAddedToPlayerHandTest() {
		GUI gui = EasyMock.mock(GUI.class);
		Player currentPlayer = EasyMock.mock(Player.class);
		Card firstCard = EasyMock.mock(Card.class);
		Card secondCard = EasyMock.mock(Card.class);
		Card thirdCard = EasyMock.mock(Card.class);


		EasyMock.expect(currentPlayer.energy).andReturn(0);
		EasyMock.expect(firstCard.cost).andReturn(1);

		EasyMock.replay(currentPlayer);
		EasyMock.replay(firstCard);

		gui.energyWarning();
		EasyMock.replay(gui);

		Gameplay g = new Gameplay(gui, currentPlayer, null, null, null);

		g.buyCard(1);

		EasyMock.verify(gui);
	}

	@Test
	public void buyCardPlayerEnergyRemovedTest() {
		GUI gui = EasyMock.mock(GUI.class);
		DeckConstructor deck = EasyMock.mock(DeckConstructor.class);
		Player currentPlayer = new Player("testPlayer");
		currentPlayer.energy = 1;
		Card firstCard = EasyMock.mock(Card.class);
		Card secondCard = EasyMock.mock(Card.class);
		Card thirdCard = EasyMock.mock(Card.class);

		EasyMock.expect(firstCard.cost).andReturn(1);
		
		Gameplay g = new Gameplay(gui, currentPlayer, null, null, null);

		//this card costs 5 points
		g.buyCard(1);
		assertEquals(currentPlayer.energy, 0);
	}

	@Test
	public void selectFirstPlayerTest() {
		Board board = new Board(1);
		Player onlyPlayer = board.playerList.get(0);

		Gameplay g = new Gameplay(null, null, null, null, null);

		g.selectFirstPlayer();
		assertFalse(g.currentplayer == onlyPlayer);
	}
}