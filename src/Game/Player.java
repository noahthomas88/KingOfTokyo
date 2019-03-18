package Game;

public class Player {
	
	private boolean isEmpty;
	public int health;
	public int victoryPoints;

	public Player() {
		this.isEmpty = false;
		this.health = 10;
		this.victoryPoints = 0;
	}

	public boolean isEmpty() {
		return this.isEmpty;
	}

}
