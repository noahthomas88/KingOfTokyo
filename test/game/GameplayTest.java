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
		Gameplay gameplay = new Gameplay(gui);
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
		Gameplay gameplay = new Gameplay(gui);
		ArrayList<Dice> dicelist = EasyMock.createNiceMock(ArrayList.class);
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
