package game;

import java.util.ArrayList;

import cards.Card;

public class Player {
	
	public String name = "";
	public int health;
	public int victoryPoints;
	public int energy;
	public int numberOfDieToRoll;
	public int numberOfDieRolls;
	public int maxHealth;
	public ArrayList<Card> cardsInHand;

	public Player(String name){
		if(name.equals("")){
			throw new IllegalArgumentException();
		}
		this.health = 10;
		this.victoryPoints = 0;
		this.energy = 0;
		this.name = name;
		this.numberOfDieToRoll = 6;
		this.numberOfDieRolls = 3;
		this.maxHealth = 10;
		this.cardsInHand = new ArrayList<Card>();
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
		if (this.health >= this.maxHealth + 1) {
			this.health = this.maxHealth;
		}
	}
	
	public void addEnergy(int i) {
		this.energy = this.energy + i;
		if(this.energy < 0) {
			this.energy = this.energy - i;
			throw new IllegalArgumentException();
		}
	}
	
	public void addVictory(int i) {
		this.victoryPoints = this.victoryPoints + i;
		if(this.victoryPoints <= -1) {
			this.victoryPoints = 0;
		}
	}

	public void addOneDie() {
		this.numberOfDieToRoll ++;	
	}

	public void subOneDie() {
		this.numberOfDieToRoll --;
	}
	
	public void addMaxHealth(){
		this.maxHealth++;
	}
	
	public void addToHand(Card card){
		this.cardsInHand.add(card);
	}
	
	public void subMaxHealth(){
		this.maxHealth--;
	}

	public String getName() {
		return this.name;
	}
	
	public int getNumberOfDieRolls(){
		return this.numberOfDieRolls;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public int getVictoryPoints(){
		return this.victoryPoints;
	}

	public int getEnergy() {
		return this.energy;
	}

	public ArrayList<Card> getCardsInHand() {
		return this.cardsInHand;
	}

}