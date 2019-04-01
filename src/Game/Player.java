package Game;

public class Player {
	
	public String name = "";
	public int health;
	public int victoryPoints;
	public int energy;
	public int numberOfDieToRoll;

	public Player(String name){
		if(name.equals("")){
			throw new IllegalArgumentException();
		}
		this.health = 10;
		this.victoryPoints = 0;
		this.energy = 0;
		this.name = name;
		this.numberOfDieToRoll = 6;
	}

	public String buildPlayerStatusString() {
		String playerStatusString = "";
		playerStatusString = "<html>name: " + this.name + "<br/>" 
				+ "health: " + this.health + "<br/>"
				+ "victory points: " + this.victoryPoints + "<br/>"
				+ "energy: " + this.energy + "</html>";
		return playerStatusString;
	}
	
	public void addHealth(int i) {
		this.health = this.health + i;
		if (this.health > 10) {
			this.health = 10;
		}
	}
	
	public void addEnergy(int i) {
		this.energy = this.energy + i;
		if(this.energy < 0) {
			this.energy = this.energy - i;
			throw new IllegalArgumentException();
		}
	}

	public void addOneDie() {
		this.numberOfDieToRoll ++;	
	}

	public void subOneDie() {
		this.numberOfDieToRoll --;
		
	}

}
