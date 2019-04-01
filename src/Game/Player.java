package Game;

public class Player {
	
	public String name = "";
	public int health;
	public int victoryPoints;
	public int energy;

	public Player(String name){
		if(name.equals("")){
			throw new IllegalArgumentException();
		}
		this.health = 10;
		this.victoryPoints = 0;
		this.energy = 0;
		this.name = name;
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
		if (i < 0) {
			throw new IllegalArgumentException();
		}
		this.health = this.health - i;
	}
	
	public void addEnergy(int i) {
		this.energy = this.energy + i;
	}

}
