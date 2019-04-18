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

		for (int index = 0; index < 6; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(index + 1);
		}

		EasyMock.replay(player, dice);

		gameplay.calculateScore(dicelist);

		EasyMock.verify(player, dice);
	}

	@Test
	public void CalculateScoreTestThreeOne() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);

		for (int index = 0; index < 3; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(1);
		}

		for (int index = 3; index < 6; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(index - 1);
		}

		player.addVictory(1);

		EasyMock.replay(player, dice);

		gameplay.calculateScore(dicelist);

		EasyMock.verify(player, dice);
	}

	@Test
	public void CalculateScoreTestThreeTwo() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);

		for (int index = 0; index < 3; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(2);
		}

		for (int index = 3; index < 6; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(index);
		}

		player.addVictory(2);

		EasyMock.replay(player, dice);

		gameplay.calculateScore(dicelist);

		EasyMock.verify(player, dice);
	}

	@Test
	public void CalculateScoreTestThreeThree() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);

		for (int index = 0; index < 3; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(3);
		}

		for (int index = 3; index < 6; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(index + 1);
		}

		player.addVictory(3);

		EasyMock.replay(player, dice);

		gameplay.calculateScore(dicelist);

		EasyMock.verify(player, dice);
	}

	@Test
	public void CalculateScoreTestFiveTwo() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);

		for (int index = 0; index < 5; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(2);
		}

		dicelist.add(dice);
		EasyMock.expect(dice.getNumberRolled()).andReturn(1);

		player.addVictory(4);

		EasyMock.replay(player, dice);

		gameplay.calculateScore(dicelist);

		EasyMock.verify(player, dice);
	}

	@Test
	public void CalculateScoreTestThreeOneThreeThree() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		Dice dice = EasyMock.strictMock(Dice.class);

		for (int index = 0; index < 3; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(3);
		}

		for (int index = 3; index < 6; index++) {
			dicelist.add(dice);
			EasyMock.expect(dice.getNumberRolled()).andReturn(1);
		}

		player.addVictory(3);
		player.addVictory(1);

		EasyMock.replay(player, dice);

		gameplay.calculateScore(dicelist);

		EasyMock.verify(player, dice);
	}

	@Test
	public void beginTurnTestCurrentPlayerInTokyo() {
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

		EasyMock.verify(gameUI, test1, board);
		;
	}

	@Test
	public void beginTurnTestCurrentPlayerNotInTokyo() {
		GUI gameUI = EasyMock.niceMock(GUI.class);
		Player test1 = EasyMock.niceMock(Player.class);
		Player test2 = EasyMock.niceMock(Player.class);
		Board board = EasyMock.niceMock(Board.class);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("test1", 0);
		map.put("test2", 1);
		Gameplay gameplay = new Gameplay(gameUI, test1, board, null, map);

		EasyMock.expect(test1.getName()).andStubReturn("test1");
		gameUI.setActivePlayer(0);
		gameUI.DisableEndTurnButton();
		EasyMock.expect(board.getCityPlayer()).andStubReturn(test2);
		gameUI.updatePlayerText(board);
		gameUI.DisableCedeButton();

		EasyMock.replay(gameUI, test1, board);

		gameplay.beginTurn();

		EasyMock.verify(gameUI, test1, board);
		;
	}

	@Test
	public void beginTurnTestTokyoEmpty() {
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
		EasyMock.expect(board.getCityPlayer()).andStubReturn(null);
		gameUI.moveToTokyo(test1);
		test1.addVictory(1);
		gameUI.updatePlayerText(board);
		gameUI.DisableCedeButton();

		EasyMock.replay(gameUI, test1, board);

		gameplay.beginTurn();

		EasyMock.verify(gameUI, test1, board);
		assertEquals(board.cityPlayer, test1);
	}

	@Test
	public void GameplayInitializationTest() {
		Board board = EasyMock.createMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		DeckConstructor deck = EasyMock.createMock(DeckConstructor.class);
		HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();
		Gameplay game = new Gameplay(ui, null, board, deck, playerToNumber);
		EasyMock.expect(ui.getNumPlayers()).andReturn(2);
		ArrayList<String> names = new ArrayList<String>();
		names.add("bla");
		names.add("bla2");
		EasyMock.expect(ui.getNames(2)).andReturn(names);
		deck.createDeck();
		deck.reveal();
		ui.setCards(null);
		EasyMock.replay(board, ui, deck);

		game.initializeGame();

		EasyMock.verify(board, ui, deck);
	}

	@Test(expected = AssertionError.class)
	public void GameplayInitializationErrorTest() {
		Board board = EasyMock.createMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		DeckConstructor deck = EasyMock.createMock(DeckConstructor.class);
		HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();
		Gameplay game = new Gameplay(ui, null, board, deck, playerToNumber);
		EasyMock.expect(ui.getNumPlayers()).andReturn(1);
		ArrayList<String> names = new ArrayList<String>();
		names.add("bla");
		names.add("bla2");
		EasyMock.expect(ui.getNames(2)).andReturn(names);
		deck.createDeck();
		deck.reveal();
		ui.setCards(null);
		EasyMock.replay(board, ui, deck);

		game.initializeGame();

		EasyMock.verify(board, ui, deck);
	}

	@Test
	public void GameplayDiceRolledTest1() {
		Board board = EasyMock.createMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		Gameplay game = new Gameplay(ui, null, board, null, null);
		Player player = EasyMock.createMock(Player.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		EasyMock.expect(player.getNumberOfDieRolls()).andReturn(0);
		Dice die = new Dice(player);
		die.numberRolled = 1;
		dice.add(die);
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);

		EasyMock.replay(board, ui);
		game.diceRolled(dice);
		EasyMock.verify(board, ui);
	}

	@Test
	public void GameplayDiceRolledTest2() {
		Board board = EasyMock.createMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		Gameplay game = new Gameplay(ui, null, board, null, null);
		Player player = EasyMock.createMock(Player.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		EasyMock.expect(player.getNumberOfDieRolls()).andReturn(0);
		Dice die = new Dice(player);
		die.numberRolled = 4;
		board.doAttack(null);
		dice.add(die);
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);

		EasyMock.replay(board, ui);
		game.diceRolled(dice);
		EasyMock.verify(board, ui);
	}

	@Test
	public void GameplayDiceRolledTest3() {
		Board board = EasyMock.createMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		Player player = new Player("bla");
		Gameplay game = new Gameplay(ui, player, board, null, null);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		Dice die = new Dice(player);
		die.numberRolled = 5;
		player.addEnergy(1);
		dice.add(die);
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);

		EasyMock.replay(board, ui);
		game.diceRolled(dice);
		EasyMock.verify(board, ui);
	}

	@Test
	public void GameplayDiceRolledTest4() {
		Board board = EasyMock.createMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		Player player = new Player("null");
		Gameplay game = new Gameplay(ui, player, board, null, null);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		EasyMock.expect(board.getCityPlayer()).andReturn(null);
		Dice die = new Dice(player);
		die.numberRolled = 6;
		dice.add(die);
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);

		EasyMock.replay(board, ui);
		game.diceRolled(dice);
		EasyMock.verify(board, ui);
	}

	@Test
	public void GameplayDiceRolledTest4two() {
		Board board = EasyMock.createMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		Player player = new Player("null");
		Gameplay game = new Gameplay(ui, null, board, null, null);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		EasyMock.expect(board.getCityPlayer()).andReturn(null);
		Dice die = new Dice(player);
		die.numberRolled = 6;
		dice.add(die);
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);

		EasyMock.replay(board, ui);
		game.diceRolled(dice);
		EasyMock.verify(board, ui);
	}

	@Test
	public void beginGameTest() {
		GUI gameUI = EasyMock.niceMock(GUI.class);
		Player test1 = EasyMock.niceMock(Player.class);
		Gameplay gameplay = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("selectFirstPlayer")
				.addMockedMethod("beginTurn").createNiceMock();
		gameplay.gameUI = gameUI;
		gameplay.currentplayer = test1;

		gameplay.selectFirstPlayer();
		EasyMock.expect(test1.getName()).andStubReturn("test1");
		gameUI.displayStartingPlayer("test1");
		gameplay.beginTurn();

		EasyMock.replay(gameplay, test1, gameUI);

		gameplay.beginGame();

		EasyMock.verify(gameplay, test1, gameUI);
	}

	@Test
	public void cedeTokyoTest() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		Gameplay gameplay = new Gameplay(ui, null, board, null, null);
		Player player = new Player("TestPlayer");
		int victoryP = player.victoryPoints;

		gameplay.currentplayer = player;

		gameplay.cedeTokyo();

		assertTrue(player.victoryPoints == victoryP + 1);
		assertTrue(board.cityPlayer.equals(player));

	}
	
	@Test
	public void swipeCardsTestPlayerHasEnoughEnergy() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		DeckConstructor deck = EasyMock.niceMock(DeckConstructor.class);
		Gameplay gameplay = new Gameplay(ui, null, board, deck, null);
		Player player = new Player("TestPlayer");
		player.energy = 6;
		int energy = player.energy;
		
		gameplay.currentplayer = player;
		
		gameplay.swipeCard();
		
		assertTrue(player.energy == energy - 2);
	}
	
	@Test
	public void swipeCardsTestPlayerDoesNotHaveEnoughEnergy() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		DeckConstructor deck = EasyMock.niceMock(DeckConstructor.class);
		Gameplay gameplay = new Gameplay(ui, null, board, deck, null);
		Player player = new Player("TestPlayer");
		player.energy = 1;
		int energy = player.energy;
		
		gameplay.currentplayer = player;
		
		gameplay.swipeCard();
		
		assertTrue(player.energy == energy);
	}
}