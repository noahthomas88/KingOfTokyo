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
	public void testTakesOneDamage() {
		Player player = new Player("Test");
		player.takesDamage(1);
		assertEquals(player.health, 9);
	}
	
	@Test
	public void testTakesTwoDamage() {
		Player player = new Player("Test");
		player.takesDamage(2);
		assertEquals(player.health, 8);
	}
	
	@Test
	public void testTakesZeroDamage() {
		Player player = new Player("Test");
		player.takesDamage(0);
		assertEquals(player.health, 10);
	}
	
	@Test
	public void testTakesNegativeDamage() {
		Player player = new Player("Test");
		try {
			player.takesDamage(-1);
			fail("Should throw IllegalArgument Exception");
		}catch(IllegalArgumentException e) {
			
		}
		
	}

}
