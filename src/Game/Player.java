package Game;

public class Player {
	
	private boolean isEmpty;
	public String name = "";
	public int health;
	public int victoryPoints;
	public int energy;

	public Player(String name) {
		this.isEmpty = false;
		this.health = 10;
		this.victoryPoints = 0;
		this.energy = 0;
		this.name = name;
	}

	public boolean isEmpty() {
		return this.isEmpty;
	}

}
