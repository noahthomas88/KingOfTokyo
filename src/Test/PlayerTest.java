package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Player;

public class PlayerTest {

	@Test
	public void testPlayerConstructor() {
		Player player = new Player("test");
		assertFalse(player.isEmpty());
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
		int dieNum = player.numberOfDieToRoll;
		assertTrue(dieNum == 6);
	}

}
