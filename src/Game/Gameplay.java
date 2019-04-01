package Game;

import java.util.ArrayList;
import java.util.Random;

public class Gameplay {

	public Player currentplayer;
	public Board gameboard;

	public Gameplay(Board myBoard) {
		this.gameboard = myBoard;
	}

	public void selectFirstPlayer() {
		ArrayList<Player> playerlist = gameboard.player;
		Integer numOfPlayers = gameboard.numOfPlayers;
		Random random = new Random();
		Integer firstplayer = random.nextInt(numOfPlayers);
		this.currentplayer = playerlist.get(firstplayer);
	}
	
}
