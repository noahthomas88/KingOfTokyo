package game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import cards.Card;
import cards.DeckConstructor;
import cards.EnergizeLogic;
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
		for (int index = 0; index < 6; index++) {
			Dice dice = EasyMock.strictMock(Dice.class);
			dicelist.add(dice);
		}

		EasyMock.replay(player);

		for (int index = 0; index < 6; index++) {
			dicelist.get(index).numberRolled = index + 1;
		}
		gameplay.calculateScore(dicelist);

		EasyMock.verify(player);
	}

	@Test
	public void CalculateScoreTestThreeOne() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		for (int index = 0; index < 6; index++) {
			Dice dice = EasyMock.strictMock(Dice.class);
			dicelist.add(dice);
		}

		player.addVictory(1);

		EasyMock.replay(player);
		
		for (int index = 0; index < 3; index++) {
			dicelist.get(index).numberRolled = 1;
		}

		for (int index = 3; index < 6; index++) {
			dicelist.get(index).numberRolled = index - 1;
		}

		gameplay.calculateScore(dicelist);

		EasyMock.verify(player);
	}

	@Test
	public void CalculateScoreTestThreeTwo() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		

		for (int index = 0; index < 6; index++) {
			Dice dice = EasyMock.strictMock(Dice.class);
			dicelist.add(dice);
		}
		
		player.addVictory(2);

		EasyMock.replay(player);

		for (int index = 0; index < 3; index++) {
			dicelist.get(index).numberRolled = 2;
		}

		for (int index = 3; index < 6; index++) {
			dicelist.get(index).numberRolled = index;
		}
		
		gameplay.calculateScore(dicelist);

		EasyMock.verify(player);
	}

	@Test
	public void CalculateScoreTestThreeThree() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		for (int index = 0; index < 6; index++) {
			Dice dice = EasyMock.strictMock(Dice.class);
			dicelist.add(dice);
		}

		player.addVictory(3);

		EasyMock.replay(player);
		
		for (int index = 0; index < 3; index++) {
			dicelist.get(index).numberRolled = 3;
		}

		for (int index = 3; index < 6; index++) {
			dicelist.get(index).numberRolled = index + 1;
		}

		gameplay.calculateScore(dicelist);

		EasyMock.verify(player);
	}

	@Test
	public void CalculateScoreTestFiveTwo() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();
		for (int index = 0; index < 6; index++) {
			Dice dice = EasyMock.strictMock(Dice.class);
			dicelist.add(dice);
		}

		player.addVictory(4);

		EasyMock.replay(player);

		for (int index = 0; index < 5; index++) {
			dicelist.get(index).numberRolled = 2;
		}

		for (int index = 5; index < 6; index++) {
			dicelist.get(index).numberRolled = 1;
		}
		
		gameplay.calculateScore(dicelist);

		EasyMock.verify(player);
	}

	@Test
	public void CalculateScoreTestThreeOneThreeThree() {
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(null, player, null, null, null);

		ArrayList<Dice> dicelist = new ArrayList<>();

		for (int index = 0; index < 6; index++) {
			Dice dice = EasyMock.strictMock(Dice.class);
			dicelist.add(dice);
		}

		player.addVictory(3);
		player.addVictory(1);

		EasyMock.replay(player);

		for (int index = 0; index < 3; index++) {
			dicelist.get(index).numberRolled = 3;
		}

		for (int index = 3; index < 6; index++) {
			dicelist.get(index).numberRolled = 1;
		}
		
		gameplay.calculateScore(dicelist);

		EasyMock.verify(player);
	}

	@Test
	public void beginTurnTestCurrentPlayerInTokyo() {
		GUI gameUI = EasyMock.strictMock(GUI.class);
		Player test1 = EasyMock.strictMock(Player.class);
		Board board = EasyMock.strictMock(Board.class);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("test1", 0);
		map.put("test2", 1);
		
		Gameplay gameplay = new Gameplay(gameUI, test1, board, null, map);

		gameUI.setActivePlayer(0);
		gameUI.DisableEndTurnButton();
		test1.addVictory(2);
		gameUI.updatePlayerText(board);
		gameUI.DisableCedeButton();
		gameUI.EnableRollButton();

		EasyMock.replay(gameUI, test1, board);

		test1.name = "test1";
		board.cityPlayer = test1;
		gameplay.beginTurn();

		EasyMock.verify(gameUI, test1);
	}

	@Test
	public void beginTurnTestCurrentPlayerNotInTokyo() {
		GUI gameUI = EasyMock.strictMock(GUI.class);
		Player test1 = EasyMock.strictMock(Player.class);
		Player test2 = EasyMock.strictMock(Player.class);
		Board board = EasyMock.strictMock(Board.class);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("test1", 0);
		map.put("test2", 1);
		Gameplay gameplay = new Gameplay(gameUI, test1, board, null, map);

		gameUI.setActivePlayer(0);
		gameUI.DisableEndTurnButton();
		gameUI.updatePlayerText(board);
		gameUI.DisableCedeButton();
		gameUI.EnableRollButton();

		EasyMock.replay(gameUI, test1, board);

		test1.name = "test1";
		board.cityPlayer = test2;
		gameplay.beginTurn();

		EasyMock.verify(gameUI);
	}

	@Test
	public void beginTurnTestTokyoEmpty() {
		GUI gameUI = EasyMock.strictMock(GUI.class);
		Player test1 = EasyMock.strictMock(Player.class);
		Board board = EasyMock.strictMock(Board.class);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("test1", 0);
		map.put("test2", 1);
		Gameplay gameplay = new Gameplay(gameUI, test1, board, null, map);

		gameUI.setActivePlayer(0);
		gameUI.DisableEndTurnButton();
		gameUI.moveToTokyo(test1);
		test1.addVictory(1);
		gameUI.updatePlayerText(board);
		gameUI.DisableCedeButton();
		gameUI.EnableRollButton();

		EasyMock.replay(gameUI, test1, board);

		test1.name = "test1";
		board.cityPlayer = null;
		gameplay.beginTurn();

		EasyMock.verify(gameUI, test1);
		assertEquals(board.cityPlayer, test1);
	}

	@Test
	public void GameplayInitializationTest() {
		Board board = EasyMock.strictMock(Board.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		DeckConstructor deck = EasyMock.strictMock(DeckConstructor.class);
		
		HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();
		ArrayList<String> names = new ArrayList<String>();
		names.add("bla");
		names.add("bla2");

		Gameplay game = new Gameplay(ui, null, board, deck, playerToNumber);
		
		EasyMock.expect(ui.inputNames(2)).andReturn(names);
		board.constructPlayers(names);
		deck.createDeck();
		deck.reveal();
		ui.displayBoard(board, 2, game);
		ui.setCards(deck.visibleCard);
		
		EasyMock.replay(board, ui, deck);

		board.numOfPlayers = 2;
		game.initializeGame(board);

		EasyMock.verify(board, ui, deck);
		assertTrue(playerToNumber.get("bla") == 0);
		assertTrue(playerToNumber.get("bla2") == 1);
	}

	@Test
	public void GameplayDiceRolledTestNumber() {
		Board board = EasyMock.strictMock(Board.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Dice die = EasyMock.strictMock(Dice.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		dice.add(die);
		
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("calculateScore").addMockedMethod("checkWin").createStrictMock();
		
		EasyMock.expect(die.numberToString(1)).andReturn("1");
		game.calculateScore(dice);
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);
		ui.DisableRollButton();
		game.checkWin();

		EasyMock.replay(die, board, ui, game);
		
		die.numberRolled = 1;
		game.gameUI = ui;
		game.gameboard = board;
		game.diceRolled(dice);
		
		EasyMock.verify(die, ui, game);
	}

	@Test
	public void GameplayDiceRolledTestEnergy() {
		Board board = EasyMock.strictMock(Board.class);
		Player player = EasyMock.strictMock(Player.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Dice die = EasyMock.strictMock(Dice.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		dice.add(die);
		
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("calculateScore").addMockedMethod("checkWin").createStrictMock();
		
		EasyMock.expect(die.numberToString(1)).andReturn("energy");
		player.addEnergy(1);
		game.calculateScore(new ArrayList<Dice>());
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);
		ui.DisableRollButton();
		game.checkWin();

		EasyMock.replay(board, player, ui, die, game);
		
		die.numberRolled = 1;
		game.gameUI = ui;
		game.gameboard = board;
		game.currentplayer = player;
		game.diceRolled(dice);
		
		EasyMock.verify(player, ui, die, game);
	}

	@Test
	public void GameplayDiceRolledTestAttack() {
		Board board = EasyMock.strictMock(Board.class);
		Player player = EasyMock.strictMock(Player.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Dice die = EasyMock.strictMock(Dice.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		dice.add(die);
		
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("calculateScore").addMockedMethod("checkWin").createStrictMock();
		
		EasyMock.expect(die.numberToString(1)).andReturn("attack");
		board.doAttack(player);
		ui.EnableCedeButton();
		game.calculateScore(new ArrayList<Dice>());
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);
		ui.DisableRollButton();
		game.checkWin();

		EasyMock.replay(board, player, ui, die, game);
		
		die.numberRolled = 1;
		game.gameUI = ui;
		game.gameboard = board;
		game.currentplayer = player;
		game.diceRolled(dice);
		
		EasyMock.verify(board, player, ui, die, game);
	}

	@Test
	public void GameplayDiceRolledTestHeal() {
		Board board = EasyMock.strictMock(Board.class);
		Player player = EasyMock.strictMock(Player.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Dice die = EasyMock.strictMock(Dice.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		dice.add(die);
		
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("calculateScore").addMockedMethod("checkWin").createStrictMock();
		
		EasyMock.expect(die.numberToString(1)).andReturn("heal");
		player.addHealth(1);
		game.calculateScore(new ArrayList<Dice>());
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);
		ui.DisableRollButton();
		game.checkWin();

		EasyMock.replay(board, player, ui, die, game);
		
		die.numberRolled = 1;
		game.gameUI = ui;
		game.gameboard = board;
		game.currentplayer = player;
		game.diceRolled(dice);
		
		EasyMock.verify(player, ui, die, game);
	}

	@Test
	public void GameplayDiceRolledTestCannotHeal() {
		Board board = EasyMock.strictMock(Board.class);
		Player player = EasyMock.strictMock(Player.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Dice die = EasyMock.strictMock(Dice.class);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		dice.add(die);
		
		Gameplay game = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("calculateScore").addMockedMethod("checkWin").createStrictMock();
		
		EasyMock.expect(die.numberToString(1)).andReturn("heal");
		game.calculateScore(new ArrayList<Dice>());
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);
		ui.DisableRollButton();
		game.checkWin();

		EasyMock.replay(board, player, ui, die, game);
		
		die.numberRolled = 1;
		game.gameUI = ui;
		game.gameboard = board;
		game.currentplayer = player;
		board.cityPlayer = player;
		game.diceRolled(dice);
		
		EasyMock.verify(ui, die, game);
	}

	@Test
	public void beginGameTest() {
		GUI gameUI = EasyMock.strictMock(GUI.class);
		Player test1 = EasyMock.strictMock(Player.class);
		Gameplay gameplay = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("selectFirstPlayer")
				.addMockedMethod("beginTurn").createStrictMock();
		gameplay.gameUI = gameUI;
		gameplay.currentplayer = test1;

		gameplay.selectFirstPlayer();
		gameUI.displayStartingPlayer("test1");
		gameplay.beginTurn();

		EasyMock.replay(gameplay, test1, gameUI);

		test1.name = "test1";
		gameplay.beginGame();

		EasyMock.verify(gameplay, test1, gameUI);
	}

	@Test
	public void cedeTokyoTest() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Gameplay gameplay = new Gameplay(ui, null, board, null, null);
		Player player = EasyMock.strictMock(Player.class);
		Player currentPlayer = EasyMock.strictMock(Player.class);

		ui.moveToTokyo(currentPlayer);
		currentPlayer.addVictory(1);
		ui.updatePlayerText(board);
		ui.DisableCedeButton();

		EasyMock.replay(player, ui);
		board.cityPlayer = player;
		gameplay.currentplayer = currentPlayer;
		gameplay.cedeTokyo();
		EasyMock.verify(player, ui);

	}
	
	@Test
	public void cedeTokyoSamePlayerTest() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		Gameplay gameplay = new Gameplay(ui, null, board, null, null);
		Player player = EasyMock.strictMock(Player.class);

		EasyMock.replay(player, ui);
		board.cityPlayer = player;
		gameplay.currentplayer = player;
		gameplay.cedeTokyo();
		EasyMock.verify(player, ui);

	}

	@Test
	public void swipeCardsTestPlayerHasEnoughEnergy() {
		Board board = EasyMock.strictMock(Board.class);
		GUI ui = EasyMock.strictMock(GUI.class);
		DeckConstructor deck = EasyMock.strictMock(DeckConstructor.class);
		Player player = EasyMock.strictMock(Player.class);
		Gameplay gameplay = new Gameplay(ui, player, board, deck, null);

		deck.swipe();
		ui.setCards(deck.visibleCard);
		player.addEnergy(-2);
		ui.updatePlayerText(board);

		EasyMock.replay(deck, ui, player);

		player.energy = 6;
		gameplay.swipeCard();

		EasyMock.verify(deck, ui, player);
	}

	@Test
	public void swipeCardsTestPlayerDoesNotHaveEnoughEnergy() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		Player player = EasyMock.niceMock(Player.class);
		Gameplay gameplay = new Gameplay(ui, player, board, null, null);

		ui.energyWarning();

		EasyMock.replay(ui, player);

		player.energy = 1;
		gameplay.swipeCard();

		EasyMock.verify(ui, player);

	}
	
	@Test
	public void useCardDoesNotHaveCardTest(){
		ArrayList<Card> hand = new ArrayList<Card>();
		Card card1 = EasyMock.strictMock(Card.class);
		Card card2 = EasyMock.strictMock(Card.class);
		hand.add(card1);
		hand.add(card2);
		
		ArrayList<Player> playerList = new ArrayList<>();
		Player test1 = EasyMock.strictMock(Player.class);
		Player test2 = EasyMock.strictMock(Player.class);
		playerList.add(test1);
		playerList.add(test2);
		
		GUI gui = EasyMock.strictMock(GUI.class);	
		Board board = EasyMock.strictMock(Board.class);		
		EnergizeLogic logic = EasyMock.strictMock(EnergizeLogic.class);
		Gameplay gameplay = new Gameplay(gui, test1, board, null, null);
		
		gui.updatePlayerText(board);
		
		EasyMock.replay(card1, card2, logic, test1, board, gui);
		
		card1.name = "Health";
		card2.name = "Energize";
		test1.cardsInHand = hand;
		gameplay.useCard("Heal");
		
		EasyMock.verify(logic, gui);	
	}
	
	@Test
	public void useCardHasCardTest() {
		ArrayList<Card> hand = new ArrayList<Card>();
		Card card = EasyMock.niceMock(Card.class);
		hand.add(card);
		
		Player test1 = EasyMock.niceMock(Player.class);
		Player test2 = EasyMock.niceMock(Player.class);
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(test1);
		playerList.add(test2);
		
		GUI gui = EasyMock.strictMock(GUI.class);
		Board board = EasyMock.niceMock(Board.class);		
		EnergizeLogic logic = EasyMock.niceMock(EnergizeLogic.class);
		Gameplay gameplay = new Gameplay(gui, test1, board, null, null);
		
		logic.use(test1, playerList);
		gui.updatePlayerText(board);

		EasyMock.replay(card, logic, test1, board, gui);
		
		card.name = "Health";
		card.logic = logic;
		card.type = "Keep";
		test1.cardsInHand = hand;
		board.playerList = playerList;
		gameplay.useCard("Health");
		
		EasyMock.verify(logic, gui);	
	}
	
	@Test
	public void useCardHasCardDiscardTest() {
		ArrayList<Card> hand = new ArrayList<Card>();
		Card card = EasyMock.niceMock(Card.class);
		hand.add(card);
		
		Player test1 = EasyMock.niceMock(Player.class);
		Player test2 = EasyMock.niceMock(Player.class);
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(test1);
		playerList.add(test2);
		
		GUI gui = EasyMock.strictMock(GUI.class);
		Board board = EasyMock.niceMock(Board.class);		
		EnergizeLogic logic = EasyMock.niceMock(EnergizeLogic.class);
		Gameplay gameplay = new Gameplay(gui, test1, board, null, null);
			
		logic.use(test1, playerList);
		gui.updatePlayerText(board);

		EasyMock.replay(card, logic, test1, board, gui);
		
		test1.cardsInHand = hand;
		board.playerList = playerList;
		card.name = "Health";
		card.logic = logic;
		card.type = "Discard";
		gameplay.useCard("Health");
		assertTrue(hand.isEmpty());
		
		EasyMock.verify(logic, gui);	
	}

	@Test
	public void checkWinTest1() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.createMock(GUI.class);
		DeckConstructor deck = EasyMock.niceMock(DeckConstructor.class);
		Player player = EasyMock.createMock(Player.class);
		Gameplay gameplay = new Gameplay(ui, player, board, deck, null);
		ui.endGame(gameplay.currentplayer, 1);
		
		EasyMock.replay(board, deck, ui, player);
		
		player.victoryPoints = 20;
		player.health = 1;
		
		gameplay.checkWin();
		
		EasyMock.verify(board, deck, ui, player);
	}

	@Test
	public void checkWinTest2() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.createMock(GUI.class);
		DeckConstructor deck = EasyMock.niceMock(DeckConstructor.class);
		Player player = EasyMock.createMock(Player.class);
		Player p1 = new Player("hi");
		Gameplay gameplay = new Gameplay(ui, player, board, deck, null);
		ArrayList<Player> playerlist = new ArrayList<Player>();
		Player p2 = new Player("hi2");
		p1.health = 0;
		p2.health = 1;
		playerlist.add(p1);
		playerlist.add(p2);
		ui.endGame(gameplay.currentplayer, 2);
		
		EasyMock.replay(board, deck, ui, player);
		
		board.playerList = playerlist;
		player.victoryPoints = 20;
		player.health = 0;
		
		gameplay.checkWin();
		
		EasyMock.verify(board, deck, ui, player);
	}

	@Test
	public void checkWinTest3() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		DeckConstructor deck = EasyMock.niceMock(DeckConstructor.class);
		Player p1 = new Player("hi");
		Gameplay gameplay = new Gameplay(ui, p1, board, deck, null);
		ArrayList<Player> playerlist = new ArrayList<Player>();
		Player p2 = new Player("hi2");
		p1.health = 0;
		p2.health = 1;
		playerlist.add(p1);
		playerlist.add(p2);
		ui.endGame(gameplay.currentplayer, 2);
		EasyMock.replay(board, deck, ui);
		board.playerList = playerlist;
		gameplay.checkWin();
		EasyMock.verify(board, deck, ui);
	}

	@Test
	public void checkWinTest4() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		DeckConstructor deck = EasyMock.niceMock(DeckConstructor.class);
		Player p1 = new Player("hi");
		Gameplay gameplay = new Gameplay(ui, p1, board, deck, null);
		ArrayList<Player> playerlist = new ArrayList<Player>();
		Player p2 = new Player("hi2");
		p1.health = 1;
		p2.health = 0;
		playerlist.add(p1);
		playerlist.add(p2);
		ui.endGame(gameplay.currentplayer, 2);
		EasyMock.replay(board, deck, ui);
		board.playerList = playerlist;
		gameplay.checkWin();
		EasyMock.verify(board, deck, ui);
	}

	@Test
	public void checkWinTest5() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		DeckConstructor deck = EasyMock.niceMock(DeckConstructor.class);
		Player p1 = new Player("hi");
		Gameplay gameplay = new Gameplay(ui, p1, board, deck, null);
		ArrayList<Player> playerlist = new ArrayList<Player>();
		Player p2 = new Player("hi2");
		p1.health = 1;
		p2.health = 1;
		playerlist.add(p1);
		playerlist.add(p2);
		EasyMock.replay(board, deck, ui);
		board.playerList = playerlist;
		gameplay.checkWin();
		EasyMock.verify(board, deck, ui);
	}

	@Test
	public void endTurnTest() {
		GUI gameUI = EasyMock.niceMock(GUI.class);
		Player test1 = EasyMock.niceMock(Player.class);
		Player test2 = EasyMock.niceMock(Player.class);
		Board board = EasyMock.niceMock(Board.class);
		ArrayList<Player> players = new ArrayList<>();
		players.add(test1);
		players.add(test2);
		board.playerList = players;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("test1", 0);
		map.put("test2", 1);
		Gameplay gameplay = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createNiceMock();
		gameplay.gameUI = gameUI;
		gameplay.currentplayer = test1;
		gameplay.gameboard = board;
		gameplay.playerToNumber = map;
		
		gameplay.beginTurn();
		
		EasyMock.replay(test1, board, gameplay);
		
		test1.name = "test1";
		board.numOfPlayers = 2;
		gameplay.endTurn();
		
		EasyMock.verify(gameplay);
		assertEquals(gameplay.currentplayer, test2);		
	}
	@Test
	public void endTurnTest2() {
		GUI gameUI = EasyMock.niceMock(GUI.class);
		Player test1 = EasyMock.niceMock(Player.class);
		Player test2 = EasyMock.niceMock(Player.class);
		Board board = EasyMock.niceMock(Board.class);
		ArrayList<Player> players = new ArrayList<>();
		players.add(test1);
		players.add(test2);
		board.playerList = players;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("test1", 0);
		map.put("test2", 1);
		Gameplay gameplay = EasyMock.partialMockBuilder(Gameplay.class).addMockedMethod("beginTurn").createNiceMock();
		gameplay.gameUI = gameUI;
		gameplay.currentplayer = test2;
		gameplay.gameboard = board;
		gameplay.playerToNumber = map;
		
		gameplay.beginTurn();
		
		EasyMock.replay(test2, board, gameplay);
		
		test2.name = "test2";
		board.numOfPlayers = 2;
		gameplay.endTurn();
		
		EasyMock.verify(gameplay);
		assertEquals(gameplay.currentplayer, test1);		
	}

	@Test
	public void buyCardUpdatesPlayerDisplayTextTest() {
		GUI gui = EasyMock.mock(GUI.class);
		Player player = EasyMock.mock(Player.class);
		Board board = EasyMock.mock(Board.class);
		Card card = EasyMock.mock(Card.class);
		DeckConstructor deck = EasyMock.mock(DeckConstructor.class);

		deck.visibleCard = new Card[3];
		for (int index = 0; index < 3; index++) {
			deck.visibleCard[index] = card;
		}

		Gameplay g = new Gameplay(gui, player, board, deck, null);

		player.addToHand(card);
		player.addEnergy(-1);
		deck.buy(0);
		gui.setCards(deck.visibleCard);
		gui.updatePlayerText(board);

		EasyMock.replay(gui, deck, player, card);

		card.cost = 1;
		player.energy = 1;
		g.buyCard(1);

		EasyMock.verify(gui, deck, player, card);
	}

	@Test
	public void buyCardFailTest() {
		GUI gui = EasyMock.mock(GUI.class);
		Player player = EasyMock.mock(Player.class);
		Board board = EasyMock.mock(Board.class);
		Card card = EasyMock.mock(Card.class);
		DeckConstructor deck = EasyMock.mock(DeckConstructor.class);

		deck.visibleCard = new Card[3];
		for (int index = 0; index < 3; index++) {
			deck.visibleCard[index] = card;
		}

		Gameplay g = new Gameplay(gui, player, board, deck, null);

		gui.energyWarning();
		EasyMock.replay(gui, deck, player, card);

		card.cost = 1;
		player.energy = 0;
		g.buyCard(1);

		EasyMock.verify(gui, deck, player, card);
	}

	@Test
	public void selectFirstPlayerTest() {
		Board board = new Board(3);
		ArrayList<String> names = new ArrayList<String>();
		names.add("first");
		names.add("second");
		names.add("third");
		board.constructPlayers(names);
		Player firstPlayer = board.playerList.get(0);
		Player secondPlayer = board.playerList.get(1);
		Player thirdPlayer = board.playerList.get(2);
		boolean flag = false;

		Gameplay g = new Gameplay(null, null, board, null, null);

		g.selectFirstPlayer();
		if (g.currentplayer == firstPlayer || g.currentplayer == secondPlayer || g.currentplayer == thirdPlayer) {
			flag = true;
		}
		assertTrue(flag);
	}

}
