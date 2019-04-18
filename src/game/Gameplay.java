package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import cards.Card;
import cards.DeckConstructor;
import main.GUI;

public class Gameplay {

	public Player currentplayer;
	public Board gameboard;
	public GUI gameUI;
	DeckConstructor deck = new DeckConstructor();
	HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();

	public Gameplay(GUI gui, Player player, Board board, DeckConstructor deck, HashMap<String, Integer> map) {
		this.gameUI = gui;
		this.currentplayer = player;
		this.gameboard = board;
		this.deck = deck;
		this.playerToNumber = map;
	}

	public void initializeGame() {

	}

	public void beginGame() {

	}

	public void beginTurn() {
	
	}

	public void diceRolled(ArrayList<Dice> dicelist) {

	}

	public void calculateScore(ArrayList<Dice> dice) {

	}

	public void endTurn() {
		if (playerToNumber.get(currentplayer.name) >= (gameboard.numOfPlayers - 1)) {
			currentplayer = gameboard.playerList.get(0);
		} else {
			currentplayer = gameboard.playerList.get(playerToNumber.get(currentplayer.name) + 1);
		}
		beginTurn();
	}
	
	public void buyCard(Card tobuy) {

	}

	public void selectFirstPlayer() {
		ArrayList<Player> playerlist = gameboard.playerList;
		Integer numOfPlayers = gameboard.numOfPlayers;
		Random random = new Random();
		Integer firstplayer = random.nextInt(numOfPlayers);
		this.currentplayer = playerlist.get(firstplayer);
	}

	public void cedeTokyo() {

	}

	public void swipeCard() {

	}

	public void useCard(String cardname) {
		
	}

}
