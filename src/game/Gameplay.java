package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import cards.Card;
import cards.DeckConstructor;
import main.GUI;

public class Gameplay {

	public Player currentplayer;
	public Board gameboard;
	public GUI gameUI;
	DeckConstructor deck = new DeckConstructor();
	HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();

	public Gameplay(GUI gui, Player player, Board board, DeckConstructor deck, HashMap<String, Integer> map) {
		this.gameUI = gui;
		this.currentplayer = player;
		this.gameboard = board;
		this.deck = deck;
		this.playerToNumber = map;
	}

	public void initializeGame() {
		int numOfPlayers;
		try {
			numOfPlayers = gameUI.getNumPlayers();
			gameboard = new Board(numOfPlayers);
		} catch (IllegalArgumentException e) {
			gameUI.playerCountWarning();
			return;
		}
		ArrayList<String> names = gameUI.getNames(numOfPlayers);
		gameboard.constructPlayers(names);
		for (int i = 0; i < gameboard.playerList.size(); i++) {
			playerToNumber.put(gameboard.playerList.get(i).name, i);
		}
		deck.createDeck();
		deck.reveal();
		gameUI.displayBoard(gameboard, numOfPlayers, this);
		gameUI.setCards(deck.visibleCard);

	}

	public void beginGame() {
		selectFirstPlayer();
		gameUI.displayStartingPlayer(currentplayer.getName());
		beginTurn();
	}

	public void beginTurn() {
		gameUI.setActivePlayer(playerToNumber.get(currentplayer.getName()));
		gameUI.DisableEndTurnButton();
		if (gameboard.getCityPlayer() == currentplayer) {
			currentplayer.addVictory(2);
		}else if (gameboard.getCityPlayer() == null) {
			gameboard.cityPlayer = currentplayer;
			gameUI.moveToTokyo(currentplayer);
			currentplayer.addVictory(1);
		}
		gameUI.updatePlayerText(gameboard);
		gameUI.DisableCedeButton();
		checkWin();
	}

	public void diceRolled(ArrayList<Dice> dicelist) {
		
		ArrayList<Dice> otherdice = new ArrayList<Dice>();
		
		for (Dice die : dicelist) {
			String result = die.numberToString(die.numberRolled);
			if (result.equals("attack")) {
				gameboard.doAttack(currentplayer);
				gameUI.EnableCedeButton();
			} else if (result.equals("heal") && currentplayer != gameboard.getCityPlayer()) {
				currentplayer.addHealth(1);
			} else if (result.equals("energy")) {
				currentplayer.addEnergy(1);
			} else {
				otherdice.add(die);
	
			}
	
		}
	
		calculateScore(otherdice);
		gameUI.EnableEndTurnButton();
		gameUI.updatePlayerText(gameboard);
	}

	public void calculateScore(ArrayList<Dice> dice) {
		int count3 = 0;
		int count2 = 0;
		int count1 = 0;
		for (Dice die : dice) {
			int number = die.getNumberRolled();
			if (number == 1) {
				count1++;
			} else if (number == 2) {
				count2++;
			} else if (number == 3) {
				count3++;
			}
		}
		if (count3 > 2) {
			currentplayer.addVictory(3 + (count3 - 3));
		}
		if (count2 > 2) {
			currentplayer.addVictory(2 + (count2 - 3));
		}
		if (count1 > 2) {
			currentplayer.addVictory(1 + (count1 - 3));
		}
		
		checkWin();
	}

	public void endTurn() {
		if (playerToNumber.get(currentplayer.name) >= (gameboard.numOfPlayers - 1)) {
			currentplayer = gameboard.playerList.get(0);
		} else {
			currentplayer = gameboard.playerList.get(playerToNumber.get(currentplayer.name) + 1);
		}
		beginTurn();
	}
	
	public void buyCard(int number) {
		Card tobuy = deck.visibleCard[number-1];
		if(currentplayer.getEnergy()>=tobuy.getCost()) {
			currentplayer.addToHand(deck.visibleCard[number-1]);
			currentplayer.addEnergy(-tobuy.getCost());
			deck.buy(number-1);
			gameUI.setCards(deck.visibleCard);
			gameUI.updatePlayerText(gameboard);
		} else {
			gameUI.energyWarning();
		}
	}

	public void selectFirstPlayer() {
		ArrayList<Player> playerlist = gameboard.playerList;
		Integer numOfPlayers = gameboard.numOfPlayers;
		Random random = new Random();
		Integer firstplayer = random.nextInt(numOfPlayers);
		this.currentplayer = playerlist.get(firstplayer);
	}

	public void cedeTokyo() {
		gameboard.cityPlayer = currentplayer;
		gameUI.moveToTokyo(currentplayer);
		currentplayer.addVictory(1);
		gameUI.DisableCedeButton();
		checkWin();
	}

	public void swipeCard() {
		int playerEnergy = currentplayer.getEnergy();
		if(playerEnergy >= 2) {
			deck.swipe();
			gameUI.setCards(deck.visibleCard);
			currentplayer.addEnergy(-2);
		} else {
			gameUI.energyWarning();
		}
	}

	public void useCard(String cardname) {
		
	}

	public void checkWin() {
		if((currentplayer.getVictoryPoints()>=20 && currentplayer.getHealth() > 0)) {
			gameUI.endGame(currentplayer);
			return;
		}
		int total = 0;
		for(Player player : gameboard.getPlayerList()) {
			if(player.getHealth() > 0) {
				total++;
			}
		}
		if(total < 2) {
			gameUI.endGame(currentplayer);
			return;
		}
		
	}

}
