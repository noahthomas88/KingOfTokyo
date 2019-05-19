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
		Player player = EasyMock.strictMock(Player.class);
		Card card = EasyMock.strictMock(Card.class);
		Board board = EasyMock.strictMock(Board.class);
		GUI gui = EasyMock.niceMock(GUI.class);
		DeckConstructor deck = EasyMock.strictMock(DeckConstructor.class);
		Gameplay game = new Gameplay(gui, player, board, deck, null);
		
		deck.visibleCard = new Card[3];
		Card toBuy = EasyMock.strictMock(Card.class);
		deck.visibleCard[0] = toBuy;
		player.energy = 4;
		toBuy.cost = 4;
		toBuy.type = "Keep";
		
		EasyMock.expect(player.haveCard("Media Friendly")).andReturn(true);
		player.addVictory(1);
		EasyMock.expect(player.haveCard("Alien Origin")).andReturn(false);
		EasyMock.expect(deck.buy(0)).andReturn(toBuy);
		player.addEnergy(-4);
		player.addToHand(toBuy);
		gui.setCards(deck.visibleCard);
		gui.updatePlayerText(board);
		
		EasyMock.replay(card, board, gui, player, deck);
		
		game.buyCard(1);
		
		EasyMock.verify(gui, player, deck);
	}
}
