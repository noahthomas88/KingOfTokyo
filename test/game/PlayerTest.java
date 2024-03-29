package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import cards.Card;
import game.Player;
import main.GUI;

public class PlayerTest {

	@Test
	public void testPlayerConstructor() {
		Player player = new Player("test");
		assertEquals(10, player.health);
		assertEquals(0, player.victoryPoints);
		assertEquals(0, player.energy);
		assertEquals(0, player.extraDie);
		assertEquals("test", player.name);
		assertEquals(10, player.maxHealth);
		assertEquals(0, player.cardsInHand.size());
	}
	
	@Test
	public void testPlayerConstructorIllegal() {
		try{
			Player player = new Player("");
			fail("should throw IllegalArgumentException");
		}catch(IllegalArgumentException e){
			
		}	
	}
	
	@Test
	public void testBuildPlayerStatusString() {
		Player player = new Player("TestDummy");
		String result = player.buildPlayerStatusString("name","health", "energy", "victory points");
		assertTrue(result.equals("<html>name: " + player.name + "<br/>" 
							+ "health: " + player.health + "<br/>"
							+ "victory points: " + player.victoryPoints + "<br/>"
							+ "energy: " + player.energy + "</html>"));
	}
	
	@Test
	public void testgetNumberofDie6() {
		Player player = new Player("TestDummy");
		assertEquals(6, player.getNumberOfDie());
	}

	@Test
	public void testAddOneHealth() {
		Player player = new Player("test");
				
		player.health = 5;
		player.maxHealth = 10;
		player.addHealth(1);
		
		assertEquals(player.health, 6);
	}
	
	@Test
	public void testAddTwoHealthTwice() {
		Player player = new Player("Test");
		player.health = 6;
		player.addHealth(2);
		player.addHealth(2);
		assertEquals(player.health, 10);
	}
	
	@Test
	public void testAddZeroHealth() {
		Player player = new Player("Test");
		player.addHealth(0);
		assertEquals(player.health, 10);
	}
	
	@Test
	public void testAddNegativeHealthAtZeroHealth() {
		Player player = new Player("test");
				
		player.health = 0;
		player.maxHealth = 10;
		player.addHealth(-1);
		
		assertEquals(player.health, 0);
	}
	
	@Test
	public void testAddNegativeHealthAtOneHealth() {
		Player player = new Player("test");
			
		GUI ui = EasyMock.niceMock(GUI.class);
		Gameplay game = EasyMock.niceMock(Gameplay.class);
		player.ui = ui;
		ui.game = game;
		
		player.health = 1;
		player.maxHealth = 10;
		player.addHealth(-1);
		
		assertEquals(player.health, 0);
	}
	
	@Test
	public void testAddNegativeHealth() {
		Player player = new Player("Test");
		player.addHealth(-1);
		assertEquals(player.health, 9);
	}
	
	@Test
	public void testAddHealthExceed() {
		Player player = new Player("Test");
		player.health = 7;
		player.addHealth(4);
		assertEquals(player.health, 10);
	}
	
	@Test
	public void testAddHealthToNine() {
		Player player = new Player("Test");
		player.health = 7;
		player.addHealth(2);
		assertEquals(player.health, 9);
	}
	
	@Test
	public void testAddOneEnergy() {
		Player player = new Player("Test");
		player.addEnergy(1);;
		assertEquals(player.energy, 1);
	}
	
	@Test
	public void testAddTwoEnergyTwice() {
		Player player = new Player("Test");
		player.addEnergy(2);
		player.addEnergy(2);
		assertEquals(player.energy, 4);
	}
	
	@Test
	public void testAddZeroEnergy() {
		Player player = new Player("Test");
		player.addEnergy(0);
		assertEquals(player.energy, 0);
	}
	
	@Test
	public void testAddNegativeEnergy() {
		Player player = new Player("Test");
		player.addEnergy(5);
		player.addEnergy(-4);
		assertEquals(player.energy, 1);
	}
	
	@Test
	public void testAddNegativeEnergyExceed() {
		Player player = new Player("Test");
		player.addEnergy(5);
		try {
			player.addEnergy(-6);
			fail("Cannot spend that much energy");
		}catch(IllegalArgumentException e) {
			assertEquals(player.energy, 5);
		}	
	}
	
	@Test
	public void testAddOneVictory() {
		Player player = new Player("Test");
		player.addVictory(1);
		assertEquals(1,player.victoryPoints);
	}
	
	@Test
	public void testAddTwoVictoryTwice() {
		Player player = new Player("Test");
		player.addVictory(2);
		player.addVictory(2);
		assertEquals(4,player.victoryPoints);
	}
	
	@Test
	public void testAddZeroVictory() {
		Player player = new Player("Test");
		player.addVictory(0);
		assertEquals(0,player.victoryPoints);
	}
	
	@Test
	public void testAddNegativeVictory() {
		Player player = new Player("Test");
		player.addVictory(5);
		player.addVictory(-3);
		assertEquals(2,player.victoryPoints);
	}
	
	@Test
	public void testAddNegativeVictoryExceed() {
		Player player = new Player("Test");
		player.addVictory(5);
		player.addVictory(-6);
		assertEquals(0,player.victoryPoints);
	}
	
	@Test
	public void testAddMaxHealth() {
		Player player = new Player("TestDummy");
		assertTrue(player.maxHealth == 10);
		player.addMaxHealth();
		assertTrue(player.maxHealth == 11);
	}
	
	@Test
	public void testSubMaxHealth() {
		Player player = new Player("TestDummy");
		assertTrue(player.maxHealth == 10);
		player.subMaxHealth();
		assertTrue(player.maxHealth == 9);
	}
	
	@Test
	public void testAddHealthWhen11() {
		Player player = new Player("TestDummy");
		player.addMaxHealth();
		player.addHealth(2);
		assertEquals(player.health, 11);		
	}

	@Test
	public void addToHandTest() {
		Player player = new Player("TestDummy");
		Card card = EasyMock.niceMock(Card.class);
		player.addToHand(card);
		assertEquals(player.cardsInHand.get(0), card);		
	}
	
	@Test
	public void HaveCardEmptyHandTest() {
		Player player = new Player("TestDummy");
		Card card1 = EasyMock.niceMock(Card.class);
		card1.name = "card";
		assertFalse(player.haveCard("card"));	
	}
	
	@Test
	public void HaveCardNotOnHandTest() {
		Player player = new Player("TestDummy");
		Card card1 = EasyMock.niceMock(Card.class);
		card1.name = "anothercard";
		player.addToHand(card1);
		assertFalse(player.haveCard("card"));	
	}
	
	@Test
	public void HaveCardOnHandTest() {
		Player player = new Player("TestDummy");
		Card card1 = EasyMock.niceMock(Card.class);
		card1.name = "card";
		player.addToHand(card1);
		assertTrue(player.haveCard("card"));	
	}
	
	@Test
	public void playerDeath() {
		Player player = new Player("TestDummy");
		GUI ui = EasyMock.niceMock(GUI.class);
		Gameplay game = EasyMock.niceMock(Gameplay.class);
		player.ui = ui;
		ui.game = game;
		player.playerDeath();
		assertEquals(player.health, 0);
	}

}
