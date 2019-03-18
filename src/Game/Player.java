package Game;

public class Player {
	
	private boolean isEmpty;
	public int health;

	public Player() {
		this.isEmpty = false;
		this.health = 10;
	}

	public boolean isEmpty() {
		return this.isEmpty;
	}

}
