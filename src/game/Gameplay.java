package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import main.GUI;

public class Gameplay {

	public Player currentplayer;
	public Board gameboard;
	public GUI gameUI;
	HashMap<String, Integer> playerToNumber = new HashMap<String, Integer>();

	public Gameplay(GUI gui) {
		this.gameUI = gui;
	}
	
	public void initializeGame() {
		
		int numOfPlayers;

		try{
			numOfPlayers = gameUI.getNumPlayers();
			gameboard = new Board(numOfPlayers);
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null, "Number of players have to be between 2 to 6");
			return;
		}		
		
		gameboard.constructPlayers(gameUI.getNames(numOfPlayers));
		for(int i=0;i<gameboard.player.size();i++) {
			playerToNumber.put(gameboard.player.get(i).name,i);
		}
		gameUI.displayBoard(gameboard, numOfPlayers);
	}
	
	public void beginGame() {
		selectFirstPlayer();
		JOptionPane.showMessageDialog(null, this.currentplayer.name+" has been selected as the starting player");
		gameUI.setActivePlayer(playerToNumber.get(currentplayer.name));
	}

	public void selectFirstPlayer() {
		ArrayList<Player> playerlist = gameboard.player;
		Integer numOfPlayers = gameboard.numOfPlayers;
		Random random = new Random();
		Integer firstplayer = random.nextInt(numOfPlayers);
		this.currentplayer = playerlist.get(firstplayer);
	}
	
}
