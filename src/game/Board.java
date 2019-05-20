package game;

import java.util.ArrayList;

import cards.DeckConstructor;
import main.GUI;

public class Board {

	public int numOfPlayers;
	public ArrayList<Player> playerList;
	public Player cityPlayer;
	public Player bayPlayer;
	public DeckConstructor deck;
	public GUI gameUI;

	public Board(int numOfPlayers, DeckConstructor deck, GUI UI) {
		if (numOfPlayers < 2 || numOfPlayers > 6) {
			throw new IllegalArgumentException();
		}
		this.gameUI = UI;
		this.numOfPlayers = numOfPlayers;
		this.playerList = new ArrayList<Player>();
		this.deck = deck;
	}

	public void constructPlayers(ArrayList<String> names) {
		for (int i = 0; i < this.numOfPlayers; i++) {
			this.playerList.add(new Player(names.get(i)));
		} 
		for (int i = 0; i < playerList.size(); i++){
			this.playerList.get(i).playerList = this.playerList;
		}
	}

	public void doAttack(Player attacker, int attack) {
		Player originalCity = cityPlayer;
		if (cityPlayer.equals(attacker) || (bayPlayer != null && bayPlayer.equals(attacker))) {
			for (Player indexedPlayer : playerList)
				if (!indexedPlayer.equals(cityPlayer) && !indexedPlayer.equals(bayPlayer)) {
					gameUI.Camouflage(indexedPlayer);
					if(!indexedPlayer.wings) {
						indexedPlayer.addHealth(attack);
					}
				}
		} else if (bayPlayer != null) {
			cityPlayer.addHealth(attack);
			bayPlayer.addHealth(attack);
		} else {
			gameUI.EnableCedeButton();
			if((!originalCity.equals(cityPlayer)) && originalCity.haveCard("Jets")) {
				return;
			}
			originalCity.addHealth(attack);
		}
	}

	public void initializeDeck() {
		deck.createDeck();
		deck.shuffle();
	}

}
