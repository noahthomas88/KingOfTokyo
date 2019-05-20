package game;

import java.util.ArrayList;

import cards.Card;

public class Player {

	public String name = "";
	public int health;
	public int victoryPoints;
	public int extraDie;
	public int energy;
	public int maxHealth;
	public int extraRoll;
	public ArrayList<Card> cardsInHand;
	public ArrayList<Player> playerList = new ArrayList<Player>();
	public boolean playerDeathThisTurn;
	public boolean wings;
	public boolean herdCuller;
	public int energyStore = 0;

	public Player(String name) {
		if (name.equals("")) {
			throw new IllegalArgumentException();
		}
		this.health = 10;
		this.victoryPoints = 0;
		this.energy = 999;
		this.extraDie = 0;
		this.name = name;
		this.maxHealth = 10;
		this.cardsInHand = new ArrayList<Card>();
		this.playerDeathThisTurn = false;
	}

	public String buildPlayerStatusString(String name, String health, String energy, String victory) {
		String playerStatusString = "";
		playerStatusString = "<html>"+ name +": " + this.name + "<br/>" + health +": " + this.health + "<br/>"
				+ victory + ": " + this.victoryPoints + "<br/>" + energy +": " + this.energy + "</html>";
		return playerStatusString;
	}

	public void addHealth(int i) {
		if(this.health > 0) {
			this.health = this.health + i;

			if (this.health >= this.maxHealth + 1) {
				this.health = this.maxHealth;
			} else if (this.health <= 0) {
				this.playerDeath();
			}
		}	
	}

	public void addEnergy(int i) {
		this.energy = this.energy + i;
		if (this.energy < 0) {
			this.energy = this.energy - i;
			throw new IllegalArgumentException();
		}
	}

	public void addVictory(int i) {
		this.victoryPoints = this.victoryPoints + i;
		if (this.victoryPoints <= -1) {
			this.victoryPoints = 0;
		}
	}

	public void addMaxHealth() {
		this.maxHealth++;
	}

	public void addToHand(Card card) {
		this.cardsInHand.add(card);
	}
	
	public void removeCard(String string) {
		for(int i = 0; i < this.cardsInHand.size(); i++) {
			if(this.cardsInHand.get(i).name.equals(string)) {
				this.cardsInHand.remove(i);
			}
		}
	}

	public void subMaxHealth() {
		this.maxHealth--;
	}

	public boolean haveCard(String string) {
		for (int j = 0; j < this.cardsInHand.size(); j++) {
			if (this.cardsInHand.get(j).name.equals(string)) {
				return true;
			}
		}
		return false;
	}

	public int getNumberOfDie() {
		return 6 + extraDie;
	}

	public int getNumberOfRolls() {
		return 3 + extraRoll;
	}
	
	public void playerDeath() {
		this.health = 0;
		this.playerDeathThisTurn = true;
	}
}
