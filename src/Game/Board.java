package Game;

public class Board {

	private int numOfPlayers;
	
	public Board(int numOfPlayers) throws IllegalArgumentException{
		this.numOfPlayers = numOfPlayers;
	}

	public int getNumOfPlayers() {
		return this.numOfPlayers;
	}
}
