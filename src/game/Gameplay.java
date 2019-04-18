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
	}

	public void diceRolled(ArrayList<Dice> dicelist) {

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
	}

	public void endTurn() {

	}
	
	public void buyCard(int number) {

	}

	public void selectFirstPlayer() {

	}

	public void cedeTokyo() {

	}

	public void swipeCard() {

	}

	public void useCard(String cardname) {
		
	}

}
