package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Player;

public class PlayerTest {

	@Test
	public void testPlayerConstructor() {
		Player player = new Player("test");
		assertTrue(player.health == 10);
		assertTrue(player.victoryPoints == 0);
		assertTrue(player.energy == 0);
		assertTrue(player.name.equals("test"));
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
		player.addEnergy(-3);
		assertEquals(player.energy, 2);
	}

}
