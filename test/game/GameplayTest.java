<<<<<<< HEAD
package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Gameplay;
import main.GUI;

public class GameplayTest {
	
	@Test
	public void GameplayInitializationTest() {
		GUI gui = EasyMock.createNiceMock(GUI.class);
		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
		EasyMock.replay(gui);
		Gameplay gameplay = new Gameplay(gui);
		gameplay.initializeGame();
		assertTrue(gameplay.gameUI==gui);
		EasyMock.verify(gui);
	}

	@Test
	public void PlayerSelectionTest() {
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		GUI gui = EasyMock.createNiceMock(GUI.class);
		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
		EasyMock.replay(gui);
		Gameplay gameplay = new Gameplay(gui);
		gameplay.initializeGame();
		assertTrue(gameplay.currentplayer == null);
		gameplay.selectFirstPlayer();
		assertTrue(gameplay.currentplayer!=null);
		EasyMock.verify(gui);
	}
	
	@Test
	public void BeginGameTest() {
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		GUI gui = EasyMock.createNiceMock(GUI.class);
		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
		EasyMock.replay(gui);
		Gameplay gameplay = new Gameplay(gui);
		gameplay.initializeGame();
		gameplay.selectFirstPlayer();
		gameplay.beginGame();
		EasyMock.verify(gui);
	}
	
	@Test
	public void BeginTurnTest() {
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		GUI gui = EasyMock.createNiceMock(GUI.class);
		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
		EasyMock.replay(gui);
		Gameplay gameplay = new Gameplay(gui);
		gameplay.initializeGame();
		gameplay.selectFirstPlayer();
		gameplay.beginGame();
		gameplay.beginTurn();
		EasyMock.verify(gui);
	}
	
	@Test
	public void EndTurnTest() {
		GUI gui = EasyMock.createNiceMock(GUI.class);
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
		EasyMock.replay(gui);
		Gameplay gameplay = new Gameplay(gui);
		gameplay.initializeGame();
		gameplay.selectFirstPlayer();
		gameplay.beginGame();
		gameplay.beginTurn();
		gameplay.endTurn();
	}
	
	@Test
	public void DiceRolledTest() {
		GUI gui = EasyMock.createNiceMock(GUI.class);
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
		EasyMock.replay(gui);
		Gameplay gameplay = new Gameplay(gui);
		
		ArrayList<Dice> dicelist = new ArrayList<Dice>();
		
		Dice numDie = EasyMock.mock(Dice.class);
		numDie.numberRolled = 3;
		EasyMock.expect(numDie.numberRolled).andReturn(3);
		EasyMock.expect(numDie.numberToString(4)).andReturn("4");
		Dice attackDie = EasyMock.mock(Dice.class);
		attackDie.numberRolled = 4;
		EasyMock.expect(attackDie.numberToString(4)).andReturn("attack");
		Dice energyDie = EasyMock.mock(Dice.class);
		energyDie.numberRolled = 5;
		EasyMock.expect(attackDie.numberToString(5)).andReturn("heal");
		Dice healDie = EasyMock.mock(Dice.class);
		EasyMock.expect(attackDie.numberToString(4)).andReturn("energy");
		healDie.numberRolled = 6;
		
		
		
		dicelist.add(numDie);
		dicelist.add(attackDie);
		dicelist.add(energyDie);
		dicelist.add(healDie);
		gameplay.initializeGame();
		gameplay.selectFirstPlayer();
		gameplay.beginGame();
		gameplay.diceRolled(dicelist);
	}
	
	@Test
	public void CalculateScoreTest() {
		GUI gui = EasyMock.createNiceMock(GUI.class);
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
		Gameplay gameplay = new Gameplay(gui);
		ArrayList<Dice> dicelist = EasyMock.createNiceMock(ArrayList.class);
		gameplay.calculateScore(dicelist);
	}
	
	@Test
	public void CedeTokyoTest() {
		GUI gui = EasyMock.createNiceMock(GUI.class);
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		EasyMock.expect(gui.getNumPlayers()).andStubReturn(2);
		EasyMock.expect(gui.getNames(2)).andStubReturn(fakenames);
		
		
		Gameplay gameplay = new Gameplay(gui);
		
		Player currentPlayer = EasyMock.mock(Player.class);
		
		gameplay.selectFirstPlayer();
		gameplay.cedeTokyo();
	}
	

}
=======
//package game;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//
//import org.easymock.EasyMock;
//import org.junit.Test;
//
//import game.Gameplay;
//import main.GUI;
//
//public class GameplayTest {
//	
//	@Test
//	public void GameplayInitializationTest() {
//		GUI gui = EasyMock.createNiceMock(GUI.class);
//		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
//		ArrayList<String> fakenames = new ArrayList<String>();
//		fakenames.add("player1");
//		fakenames.add("player2");
//		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
//		EasyMock.replay(gui);
//		Gameplay gameplay = new Gameplay(gui);
//		gameplay.initializeGame();
//		assertTrue(gameplay.gameUI==gui);
//		EasyMock.verify(gui);
//	}
//
//	@Test
//	public void PlayerSelectionTest() {
//		ArrayList<String> fakenames = new ArrayList<String>();
//		fakenames.add("player1");
//		fakenames.add("player2");
//		GUI gui = EasyMock.createNiceMock(GUI.class);
//		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
//		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
//		EasyMock.replay(gui);
//		Gameplay gameplay = new Gameplay(gui);
//		gameplay.initializeGame();
//		assertTrue(gameplay.currentplayer == null);
//		gameplay.selectFirstPlayer();
//		assertTrue(gameplay.currentplayer!=null);
//		EasyMock.verify(gui);
//	}
//	
//	@Test
//	public void BeginGameTest() {
//		ArrayList<String> fakenames = new ArrayList<String>();
//		fakenames.add("player1");
//		fakenames.add("player2");
//		GUI gui = EasyMock.createNiceMock(GUI.class);
//		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
//		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
//		EasyMock.replay(gui);
//		Gameplay gameplay = new Gameplay(gui);
//		gameplay.initializeGame();
//		gameplay.beginGame();
//		EasyMock.verify(gui);
//	}
//	
//	@Test
//	public void BeginTurnTest() {
//		ArrayList<String> fakenames = new ArrayList<String>();
//		fakenames.add("player1");
//		fakenames.add("player2");
//		GUI gui = EasyMock.createNiceMock(GUI.class);
//		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
//		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
//		EasyMock.replay(gui);
//		Gameplay gameplay = new Gameplay(gui);
//		gameplay.beginTurn();
//		EasyMock.verify(gui);
//	}
//	
//	@Test
//	public void EndTurnTest() {
//		GUI gui = EasyMock.createNiceMock(GUI.class);
//		ArrayList<String> fakenames = new ArrayList<String>();
//		fakenames.add("player1");
//		fakenames.add("player2");
//		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
//		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
//		Gameplay gameplay = new Gameplay(gui);
//		gameplay.endTurn();
//	}
//	
//	@Test
//	public void DiceRolledTest() {
//		GUI gui = EasyMock.createNiceMock(GUI.class);
//		ArrayList<String> fakenames = new ArrayList<String>();
//		fakenames.add("player1");
//		fakenames.add("player2");
//		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
//		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
//		Gameplay gameplay = new Gameplay(gui);
//		ArrayList<Dice> dicelist = EasyMock.createNiceMock(ArrayList.class);
//		gameplay.diceRolled(dicelist);
//	}
//	
//	@Test
//	public void CalculateScoreTest() {
//		GUI gui = EasyMock.createNiceMock(GUI.class);
//		ArrayList<String> fakenames = new ArrayList<String>();
//		fakenames.add("player1");
//		fakenames.add("player2");
//		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
//		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
//		Gameplay gameplay = new Gameplay(gui);
//		ArrayList<Dice> dicelist = EasyMock.createNiceMock(ArrayList.class);
//		gameplay.calculateScore(dicelist);
//	}
//	
//	@Test
//	public void CedeTokyoTest() {
//		GUI gui = EasyMock.createNiceMock(GUI.class);
//		ArrayList<String> fakenames = new ArrayList<String>();
//		fakenames.add("player1");
//		fakenames.add("player2");
//		EasyMock.expect(gui.getNumPlayers()).andReturn(2);
//		EasyMock.expect(gui.getNames(2)).andReturn(fakenames);
//		Gameplay gameplay = new Gameplay(gui);
//		gameplay.cedeTokyo();
//	}
//	
//
//}
>>>>>>> 1877aea2c43a88707744bf5a430b50a35b1a573e
