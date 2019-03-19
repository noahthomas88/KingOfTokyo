package Game;

public class Board {

	public int numOfPlayers;
	public Player[] player;
	
	public Board(int numOfPlayers){
		if(numOfPlayers < 2 || numOfPlayers > 6){
			throw new IllegalArgumentException();
		}
		this.numOfPlayers = numOfPlayers;
	}
	
}
