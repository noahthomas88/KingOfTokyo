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
		gameplay.beginGame();
		EasyMock.verify(gui);
	}
	

}
