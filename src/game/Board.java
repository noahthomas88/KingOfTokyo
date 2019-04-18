package game;

import java.util.ArrayList;

import cards.DeckConstructor;

public class Board {

	public int numOfPlayers;
	public ArrayList<Player> playerList;
	public Player cityPlayer;
	public Player bayPlayer;
	public DeckConstructor deck;
	
	public Board(int numOfPlayers) {
		if (numOfPlayers < 2 || numOfPlayers > 6) {
			throw new IllegalArgumentException();
		}

		this.numOfPlayers = numOfPlayers;
		this.playerList = new ArrayList<Player>();
		this.deck = new DeckConstructor();
	}

	public void constructPlayers(ArrayList<String> names) {
		for (int i = 0; i < this.numOfPlayers; i++) {
			this.playerList.add(new Player(names.get(i)));
		}
	}
	
	public void doAttack(Player attacker) {
		if(cityPlayer.equals(attacker) || (bayPlayer != null && bayPlayer.equals(attacker))) {
			for(int i = 0; i < playerList.size(); i++) {
				Player indexedPlayer = playerList.get(i);
				if(!indexedPlayer.equals(cityPlayer)){
					if(bayPlayer == null){
						indexedPlayer.addHealth(-1);
					} else if(!indexedPlayer.equals(bayPlayer)){
						indexedPlayer.addHealth(-1);
					}
				}
			}
		} else if (bayPlayer != null){
			cityPlayer.addHealth(-1);
			bayPlayer.addHealth(-1);
		} else {
			cityPlayer.addHealth(-1);
		}
	}
	
	public void initializeDeck() {
		deck.createDeck();
		deck.shuffle();
	}
	
	public Player getCityPlayer() {
		return this.cityPlayer;
	}
	
	public int getPlayerListSize() {
		return playerList.size();
	}

}
