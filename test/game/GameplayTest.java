package game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import cards.DeckConstructor;
import game.Gameplay;
import main.GUI;

public class GameplayTest {
	
	@Test
	public void GameplayConstructorTest() {
		Player currentplayer = new Player("null");
		Board gameboard = new Board(2);
		GUI gameUI = new GUI();
		DeckConstructor deck = new DeckConstructor();
		HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();
		Gameplay game = new Gameplay(gameUI, currentplayer, gameboard, deck, playerToNumber);
		assertEquals(game.gameUI, gameUI);
		assertEquals(game.gameboard, gameboard);
		assertEquals(game.playerToNumber, playerToNumber);
		assertEquals(game.deck, deck);
	}
	
	@Test
	public void CalculateScoreTestNoduplication() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);
		
		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);
				
		for(int index = 0; index < 6; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(index+1);
		}
		
		EasyMock.replay(player, dice);
		
		gameplay.calculateScore(dicelist);
		
		EasyMock.verify(player,dice);
	}
	
	@Test
	public void CalculateScoreTestThreeOne() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);
		
		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);
				
		for(int index = 0; index < 3; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(1);
		}
		
		for(int index = 3; index < 6; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(index-1);
		}
		
		player.addVictory(1);
		
		EasyMock.replay(player, dice);
		
		gameplay.calculateScore(dicelist);
		
		EasyMock.verify(player,dice);
	}
	
	@Test
	public void CalculateScoreTestThreeTwo() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);
		
		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);
				
		for(int index = 0; index < 3; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(2);
		}
		
		for(int index = 3; index < 6; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(index);
		}
		
		player.addVictory(2);
		
		EasyMock.replay(player, dice);
		
		gameplay.calculateScore(dicelist);
		
		EasyMock.verify(player,dice);
	}
	
	@Test
	public void CalculateScoreTestThreeThree() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);
		
		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);
				
		for(int index = 0; index < 3; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(3);
		}
		
		for(int index = 3; index < 6; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(index+1);
		}
		
		player.addVictory(3);
		
		EasyMock.replay(player, dice);
		
		gameplay.calculateScore(dicelist);
		
		EasyMock.verify(player,dice);
	}
	
	@Test
	public void CalculateScoreTestFiveTwo() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);
		
		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);
				
		for(int index = 0; index < 5; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(2);
		}
		
		dicelist.add(dice);
		EasyMock.expect(dice.getNumberRolled()).andReturn(1);
		
		player.addVictory(4);
		
		EasyMock.replay(player, dice);
		
		gameplay.calculateScore(dicelist);
		
		EasyMock.verify(player,dice);
	}
	
	@Test
	public void CalculateScoreTestThreeOneThreeThree() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);
		
		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);
				
		for(int index = 0; index < 3; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(3);
		}
		
		for(int index = 3; index < 6; index++){
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(1);
		}
		
		player.addVictory(3);
		player.addVictory(1);	
		
		EasyMock.replay(player, dice);
		
		gameplay.calculateScore(dicelist);
		
		EasyMock.verify(player,dice);
	}
	
	@Test
	public void beginTurnTestCurrentPlayerInTokyo(){
		GUI gameUI = EasyMock.niceMock(GUI.class);
		Player test1 = EasyMock.niceMock(Player.class);
		Board board = EasyMock.niceMock(Board.class);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("test1", 0);
		map.put("test2", 1);
		Gameplay gameplay = new Gameplay(gameUI, test1, board, null, map);
		
		EasyMock.expect(test1.getName()).andStubReturn("test1");
		gameUI.setActivePlayer(0);
		gameUI.DisableEndTurnButton();
		EasyMock.expect(board.getCityPlayer()).andStubReturn(test1);
		test1.addVictory(2);
		gameUI.updatePlayerText(board);
		gameUI.DisableCedeButton();
		
		EasyMock.replay(gameUI, test1, board);
		
		gameplay.beginTurn();
		
		EasyMock.verify(gameUI, test1, board);;
		
		
	}

}