package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import game.Player;

public class RootingForTheUnderdogLogicTest {

	@Test
	public void rootingForTheUnderdogLogicUseTest() {
		Player p = new Player("NewPlayer");
		Player p2 = new Player("NewPlayer2");
		Player p3 = new Player("NewPlayer3");
		
		p.victoryPoints = 10;
		p2.victoryPoints = 11;
		p3.victoryPoints = 12;
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		
		RootingForTheUnderdogLogic rootingForTheUnderdogLogic = new RootingForTheUnderdogLogic();
		rootingForTheUnderdogLogic.use(p, players);
		
		assertTrue(p.victoryPoints == 11);
		assertTrue(p2.victoryPoints == 11);
		assertTrue(p3.victoryPoints == 12);
			
	}
	
	@Test
	public void rootingForTheUnderdogLogicNoEffectTest() {
		Player p = new Player("NewPlayer");
		Player p2 = new Player("NewPlayer2");
		Player p3 = new Player("NewPlayer3");
		
		p.victoryPoints = 13;
		p2.victoryPoints = 11;
		p3.victoryPoints = 12;
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		
		RootingForTheUnderdogLogic rootingForTheUnderdogLogic = new RootingForTheUnderdogLogic();
		rootingForTheUnderdogLogic.use(p, players);
		
		assertTrue(p.victoryPoints == 13);
		assertTrue(p2.victoryPoints == 11);
		assertTrue(p3.victoryPoints == 12);
			
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testUse() {
		RootingForTheUnderdogLogic logic = new RootingForTheUnderdogLogic();
		logic.use(null);
	}

}
