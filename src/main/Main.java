package main;

import game.Board;
import game.Gameplay;

public class Main {

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.inputLanguage();
		Gameplay game = new Gameplay(gui, null, null, null, null);
		Board gameboard;
		try {
			int numOfPlayers = gui.inputNumPlayers();
			gameboard = new Board(numOfPlayers);
		} catch (IllegalArgumentException e) {
			gui.playerCountWarning();
			return;
		}
		game.initializeGame(gameboard);
		game.beginGame();
	}

}
