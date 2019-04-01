package Game;

public class Player {
	
	private boolean isEmpty;
	public String name = "";
	public int health;
	public int victoryPoints;
	public int energy;
	public int numberOfDieToRoll;

	public Player(String name){
		if(name.equals("")){
			throw new IllegalArgumentException();
		}
		this.isEmpty = false;
		this.health = 10;
		this.victoryPoints = 0;
		this.energy = 0;
		this.name = name;
		this.numberOfDieToRoll = 6;
	}

	public boolean isEmpty() {
		return this.isEmpty;
	}

	public String buildPlayerStatusString() {
		String playerStatusString = "";
		playerStatusString = "<html>name: " + this.name + "<br/>" 
				+ "health: " + this.health + "<br/>"
				+ "victory points: " + this.victoryPoints + "<br/>"
				+ "energy: " + this.energy + "</html>";
		return playerStatusString;
	}

	public void addOneDie() {
		this.numberOfDieToRoll ++;	
	}

}
