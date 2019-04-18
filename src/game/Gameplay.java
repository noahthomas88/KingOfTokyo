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

	}

	public void beginTurn() {
	
	}

	public void diceRolled(ArrayList<Dice> dicelist) {

	}

	public void calculateScore(ArrayList<Dice> dice) {

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
