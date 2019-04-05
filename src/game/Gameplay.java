package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import main.GUI;

public class Gameplay {

	public Player currentplayer;
	public Board gameboard;
	public GUI gameUI;
	HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();

	public Gameplay(GUI gui) {
		this.gameUI = gui;
	}

	public void initializeGame() {

		int numOfPlayers;

		try {
			numOfPlayers = gameUI.getNumPlayers();
			gameboard = new Board(numOfPlayers);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Number of players have to be between 2 to 6");
			return;
		}

		gameboard.constructPlayers(gameUI.getNames(numOfPlayers));
		for (int i = 0; i < gameboard.playerList.size(); i++) {
			playerToNumber.put(gameboard.playerList.get(i).name, i);
		}
		gameUI.displayBoard(gameboard, numOfPlayers, this);
	}

	public void beginGame() {
		selectFirstPlayer();
		gameUI.displayStartingPlayer(currentplayer.name);
		beginTurn();

	}

	public void runCardEffects() {
		System.out.println("running card effects");
	}

	public void beginTurn() {
		gameUI.setActivePlayer(playerToNumber.get(currentplayer.name));
		gameUI.DisableEndTurnButton();
		if (gameboard.cityPlayer == currentplayer) {
			currentplayer.addVictory(2);
		}
		if (gameboard.cityPlayer == null) {
			gameboard.cityPlayer = currentplayer;
			gameUI.moveToTokyo(currentplayer);
			currentplayer.addVictory(1);
		}
		gameUI.updatePlayerText(gameboard);
		runCardEffects();
	}

	public void diceRolled(ArrayList<Dice> dicelist) {
		ArrayList<Dice> otherdice = new ArrayList<Dice>();
		for (Dice die : dicelist) {
			String result = die.numberToString(die.numberRolled);
			if (result.equals("attack")) {
				gameboard.doAttack(currentplayer);
			} else if (result.equals("heal") && currentplayer != gameboard.cityPlayer) {
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
			if (die.numberRolled == 1) {
				count1++;
			} else if (die.numberRolled == 2) {
				count2++;
			} else {
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
	}

	public void endTurn() {
		if (playerToNumber.get(currentplayer.name) >= (gameboard.numOfPlayers - 1)) {
			currentplayer = gameboard.playerList.get(0);
		} else {
			currentplayer = gameboard.playerList.get(playerToNumber.get(currentplayer.name) + 1);
		}
		beginTurn();
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
	}

}
