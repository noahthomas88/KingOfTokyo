package cards;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Gameplay;
import game.Player;
import main.GUI;

public class MediaFriendlyLogicTest {
	
	@Test
	public void testBuyCardHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();
		DeckConstructor deck = EasyMock.strictMock(DeckConstructor.class);
		
		game.deck = deck;

		card.name = "Solar Powered";
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		player.victoryPoints = 0;
		player.energy = 4;
		player.addToHand(card);
		HashMap<String, Integer> playerToNumber= new HashMap<>();
		playerToNumber.put("test", 0);
		
		game.currentplayer = player;
		game.gameboard = board;
		game.playerToNumber = playerToNumber;
		game.gameUI = gui;
		
		Card testCard = EasyMock.strictMock(Card.class);
		testCard.name = "TestCard";
		testCard.cost = 4;
		game.deck.visibleCard = new Card[3];
		
		
		game.deck.visibleCard[0] = testCard;
		
		game.buyCard(1);
		
		assertEquals(player.victoryPoints, 1);
	}
	
	@Test
	public void testBuyTwoCardsHaveCard() {
		Player player = new Player("test");
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createStrictMock();

		card.name = "Solar Powered";
		board.numOfPlayers = 1;
		board.playerList = new ArrayList<>();
		board.playerList.add(player);
		
		player.victoryPoints = 0;
		player.addToHand(card);
		HashMap<String, Integer> playerToNumber= new HashMap<>();
		playerToNumber.put("test", 0);
		
		game.currentplayer = player;
		game.gameboard = board;
		game.playerToNumber = playerToNumber;
		game.gameUI = gui;
		
		
		Card card2 = EasyMock.strictMock(Card.class);
		card.name = "TestCard";
		game.deck.visibleCard[0] = card;
		game.deck.visibleCard[1] = card2;
		
		
		game.buyCard(0);
		game.buyCard(1);
		
		assertEquals(player.victoryPoints, 2);
	}

}
