package main;

import game.Gameplay;

public class Main {

	public static void main(String[] args) {
		GUI gui = new GUI();
		Gameplay game = new Gameplay(gui, null, null, null, null);
		game.initializeGame();
		game.beginGame();
	}

}
