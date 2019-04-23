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
		Board board = EasyMock.createNiceMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		Player player = new Player("test");
		Gameplay game = new Gameplay(ui, player, board, null, null);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		Dice die = new Dice(player);
		die.numberRolled = 1;
		dice.add(die);
		ui.EnableEndTurnButton();
		ui.updatePlayerText(board);
		ArrayList<Player> playerlist = new ArrayList<Player>();
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);

		EasyMock.replay(board, ui);
		game.diceRolled(dice);
		EasyMock.verify(board, ui);
	}

	@Test
	public void GameplayDiceRolledTest2() {
		Board board = EasyMock.createNiceMock(Board.class);
		GUI ui = EasyMock.createNiceMock(GUI.class);
		Player player = new Player("test");
		Gameplay game = new Gameplay(ui, player, board, null, null);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		ArrayList<Player> playerlist = new ArrayList<Player>();
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);
		Dice die = new Dice(player);
		die.numberRolled = 4;

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
		ArrayList<Player> playerlist = new ArrayList<Player>();
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);
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
		EasyMock.expect(board.getCityPlayer()).andReturn(player);
		Dice die = new Dice(player);
		ArrayList<Player> playerlist = new ArrayList<Player>();
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);
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
		Gameplay game = new Gameplay(ui, player, board, null, null);
		ArrayList<Dice> dice = new ArrayList<Dice>();
		EasyMock.expect(board.getCityPlayer()).andReturn(null);
		ArrayList<Player> playerlist = new ArrayList<Player>();
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);
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
		GUI ui = EasyMock.strictMock(GUI.class);
		Gameplay gameplay = new Gameplay(ui, null, board, null, null);
		Player player = EasyMock.strictMock(Player.class);

		ui.moveToTokyo(player);
		player.addVictory(1);
		ui.updatePlayerText(board);
		ui.DisableCedeButton();

		EasyMock.replay(player, ui);
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

		EasyMock.expect(player.getEnergy()).andReturn(6);
		deck.swipe();
		ui.setCards(deck.visibleCard);
		player.addEnergy(-2);
		ui.updatePlayerText(board);

		EasyMock.replay(deck, ui, player);

		gameplay.swipeCard();

		EasyMock.verify(deck, ui, player);
	}

	@Test
	public void swipeCardsTestPlayerDoesNotHaveEnoughEnergy() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.niceMock(GUI.class);
		Player player = EasyMock.niceMock(Player.class);
		Gameplay gameplay = new Gameplay(ui, player, board, null, null);

		EasyMock.expect(player.getEnergy()).andReturn(1);
		ui.energyWarning();

		EasyMock.replay(ui, player);

		gameplay.swipeCard();

		EasyMock.verify(ui, player);

	}
	
	@Test
	public void useCardDoesNotHaveCardTest(){
		ArrayList<Card> hand = new ArrayList<Card>();
		Card card = EasyMock.niceMock(Card.class);
		hand.add(card);
		hand.add(card);
		
		Player test1 = EasyMock.niceMock(Player.class);
		Player test2 = EasyMock.niceMock(Player.class);
		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(test1);
		playerList.add(test2);
		
		Board board = EasyMock.niceMock(Board.class);		
		EnergizeLogic logic = EasyMock.strictMock(EnergizeLogic.class);
		Gameplay gameplay = new Gameplay(null, test1, board, null, null);
		
		EasyMock.expect(test1.getCardsInHand()).andReturn(hand);
		EasyMock.expect(card.getName()).andReturn("Health");
		EasyMock.expect(card.getName()).andReturn("Energize");
		
		EasyMock.replay(card, logic, test1, board);
		
		gameplay.useCard("Heal");
		
		EasyMock.verify(logic);	
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
		
		Board board = EasyMock.niceMock(Board.class);		
		EnergizeLogic logic = EasyMock.niceMock(EnergizeLogic.class);
		Gameplay gameplay = new Gameplay(null, test1, board, null, null);
		
		EasyMock.expect(test1.getCardsInHand()).andReturn(hand);
		EasyMock.expect(card.getName()).andReturn("Health");
		EasyMock.expect(card.getCardLogic()).andReturn(logic);	
		EasyMock.expect(board.getPlayerList()).andReturn(playerList);
		logic.use(test1, playerList);
		EasyMock.expect(card.getType()).andReturn("Keep");

		EasyMock.replay(card, logic, test1, board);
		
		gameplay.useCard("Health");
		
		EasyMock.verify(logic);	
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
		
		Board board = EasyMock.niceMock(Board.class);		
		EnergizeLogic logic = EasyMock.niceMock(EnergizeLogic.class);
		Gameplay gameplay = new Gameplay(null, test1, board, null, null);
		
		EasyMock.expect(test1.getCardsInHand()).andReturn(hand);
		EasyMock.expect(card.getName()).andReturn("Health");
		EasyMock.expect(card.getCardLogic()).andReturn(logic);	
		EasyMock.expect(board.getPlayerList()).andReturn(playerList);
		logic.use(test1, playerList);
		EasyMock.expect(card.getType()).andReturn("Discard");
		EasyMock.expect(test1.getCardsInHand()).andReturn(hand);

		EasyMock.replay(card, logic, test1, board);
		
		gameplay.useCard("Health");
		assertTrue(hand.isEmpty());
		
		EasyMock.verify(logic);	
	}

	@Test
	public void checkWinTest1() {
		Board board = EasyMock.niceMock(Board.class);
		GUI ui = EasyMock.createMock(GUI.class);
		DeckConstructor deck = EasyMock.niceMock(DeckConstructor.class);
		Player player = EasyMock.createMock(Player.class);
		Gameplay gameplay = new Gameplay(ui, player, board, deck, null);
		EasyMock.expect(player.getVictoryPoints()).andReturn(20);
		EasyMock.expect(player.getHealth()).andReturn(1);
		ui.endGame(gameplay.currentplayer);
		EasyMock.replay(board, deck, ui, player);
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
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);
		EasyMock.expect(player.getVictoryPoints()).andReturn(20);
		EasyMock.expect(player.getHealth()).andReturn(0);
		ui.endGame(gameplay.currentplayer);
		EasyMock.replay(board, deck, ui, player);
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
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);
		ui.endGame(gameplay.currentplayer);
		EasyMock.replay(board, deck, ui);
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
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);
		ui.endGame(gameplay.currentplayer);
		EasyMock.replay(board, deck, ui);
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
		EasyMock.expect(board.getPlayerList()).andReturn(playerlist);
		EasyMock.replay(board, deck, ui);
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
		
		EasyMock.expect(test1.getName()).andReturn("test1");
		EasyMock.expect(board.getPlayerListSize()).andReturn(2);
		gameplay.beginTurn();
		
		EasyMock.replay(test1, board, gameplay);
		
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
		
		EasyMock.expect(test2.getName()).andReturn("test2");
		EasyMock.expect(board.getPlayerListSize()).andReturn(2);
		gameplay.beginTurn();
		
		EasyMock.replay(test2, board, gameplay);
		
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

		EasyMock.expect(player.getEnergy()).andReturn(1);
		EasyMock.expect(card.getCost()).andReturn(1);
		player.addToHand(card);
		EasyMock.expect(card.getCost()).andReturn(1);
		player.addEnergy(-1);
		deck.buy(0);
		gui.setCards(deck.visibleCard);
		gui.updatePlayerText(board);

		EasyMock.replay(gui, deck, player, card);

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

		EasyMock.expect(player.getEnergy()).andReturn(0);
		EasyMock.expect(card.getCost()).andReturn(1);

		gui.energyWarning();
		EasyMock.replay(gui, deck, player, card);

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