package Game;

import java.util.ArrayList;

public class Board {

	public int numOfPlayers;
	public ArrayList<Player> player;
	public Player tokyoCity;
	public Player tokyoBay;
	
	public Board(int numOfPlayers) {
		if (numOfPlayers < 2 || numOfPlayers > 6) {
			throw new IllegalArgumentException();
		}

		this.numOfPlayers = numOfPlayers;
		this.player = new ArrayList<Player>();
	}

	public void constructPlayers(ArrayList<String> names) {
		for (int i = 0; i < this.numOfPlayers; i++) {
			this.player.add(new Player(names.get(i)));
		}
	}

}
