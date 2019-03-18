package Game;

public class Board {

	private int numOfPlayers;
	
	public Board(int numOfPlayers){
		if(numOfPlayers < 2 || numOfPlayers > 6){
			throw new IllegalArgumentException();
		}
		this.numOfPlayers = numOfPlayers;
	}

	public int getNumOfPlayers() {
		return this.numOfPlayers;
	}
}
