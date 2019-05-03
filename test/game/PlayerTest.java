package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import cards.Card;
import game.Player;

public class PlayerTest {

	@Test
	public void testPlayerConstructor() {
		Player player = new Player("test");
		assertTrue(player.health == 10);
		assertTrue(player.victoryPoints == 0);
		assertTrue(player.energy == 0);
		assertTrue(player.name.equals("test"));
		assertTrue(player.numberOfDieRolls == 3);
		assertTrue(player.numberOfDieToRoll == 6);
		assertTrue(player.maxHealth == 10);
		assertTrue(player.cardsInHand.size() == 0);
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
		String result = player.buildPlayerStatusString();
		assertTrue(result.equals("<html>name: " + player.name + "<br/>" 
							+ "health: " + player.health + "<br/>"
							+ "victory points: " + player.victoryPoints + "<br/>"
							+ "energy: " + player.energy + "</html>"));
	}
	
	@Test
	public void testPlayerNumberOfDie() {
		Player player = new Player("TestDummy");
		assertTrue(player.numberOfDieToRoll == 6);
	}
	
	@Test
	public void testAddOneDie() {
		Player player = new Player("TestDummy");
		assertTrue(player.numberOfDieToRoll == 6);
		player.addOneDie();
		assertTrue(player.numberOfDieToRoll == 7);
	}
	
	@Test
	public void testSubOneDie() {
		Player player = new Player("TestDummy");
		assertTrue(player.numberOfDieToRoll == 6);
		player.subOneDie();
		assertTrue(player.numberOfDieToRoll == 5);
	}

	@Test
	public void testAddOneHealth() {
		Player player = new Player("Test");
		player.health = 5;
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
	public void getNameTest() {
		Player player = new Player("TestDummy");
		assertEquals(player.getName(), "TestDummy");		
	}
	
	@Test
	public void getNumberOfDieRollsTest() {
		Player player = new Player("TestDummy");
		assertEquals(player.getNumberOfDieRolls(), 3);		
	}
	
	@Test
	public void getEnergyTest() {
		Player player = new Player("TestDummy");
		player.energy = 6;
		assertEquals(player.getEnergy(), 6);
	}
	
	@Test
	public void getHealthTest() {
		Player player = new Player("TestDummy");
		player.health = 1;
		assertEquals(player.getHealth(), 1);		
	}
	
	@Test
	public void getVictoryPointsTest() {
		Player player = new Player("TestDummy");
		player.victoryPoints = 1;
		assertEquals(player.getVictoryPoints(), 1);		
	}
	
	@Test
	public void addToHandTest() {
		Player player = new Player("TestDummy");
		Card card = EasyMock.niceMock(Card.class);
		player.addToHand(card);
		assertEquals(player.cardsInHand.get(0), card);		
	}

}
