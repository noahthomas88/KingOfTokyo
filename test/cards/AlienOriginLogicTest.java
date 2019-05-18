package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Gameplay;
import game.Player;
import main.GUI;

public class AlienOriginLogicTest {

	@Test
	public void testLoseOneHealthHaveCard() {
		Player player = new Player("test");
		Card alienOriginCard = EasyMock.mock(Card.class);
		Card cardToPurchase = EasyMock.mock(Card.class);
		DeckConstructor deck = EasyMock.strictMock(DeckConstructor.class);
		GUI gui = EasyMock.mock(GUI.class);
		Gameplay gameplay = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("useCard").createStrictMock();
		
		gameplay.currentplayer = player;
		gameplay.gameUI = gui;
		gameplay.deck = deck;
		alienOriginCard.name = "Alien Origin";
		player.energy = 3;
		Card[] list = new Card[1];
		list[0] = cardToPurchase;
		deck.visibleCard = list;
		cardToPurchase.cost = 4;
		cardToPurchase.name = "Apartment Building";
		cardToPurchase.type = "Discard";
		player.cardsInHand = new ArrayList<>();
		player.addToHand(alienOriginCard);
		EasyMock.expect(deck.buy(0)).andReturn(cardToPurchase);
		gameplay.useCard("Apartment Building");
		
		EasyMock.replay(gameplay, deck);
			
		gameplay.buyCard(1);
		assertEquals(player.energy, 0);
	
		EasyMock.verify(gameplay);
	}
}
