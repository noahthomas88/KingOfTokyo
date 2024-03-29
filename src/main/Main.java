package main;

import cards.DeckConstructor;
import game.Board;
import game.Gameplay;

public class Main {

	public static void main(String[] args) {
		GUI gui = new GUI();
		Board gameboard;
		DeckConstructor deck = new DeckConstructor(gui.locale);
		try {
			int numOfPlayers = gui.inputNumPlayers();
			gameboard = new Board(numOfPlayers, deck, gui);
		} catch (IllegalArgumentException e) {
			gui.playerCountWarning();
			return;
		}
		Gameplay game = new Gameplay(gui, null, gameboard, null, null);
		game.initializeGame(gameboard);
		deck.setUI(gui);
		game.beginGame();
	}

}
