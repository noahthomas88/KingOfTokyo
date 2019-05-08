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

	public void doAttack(Player attacker, int attack) {
		if (cityPlayer.equals(attacker) || (bayPlayer != null && bayPlayer.equals(attacker))) {
			for (Player indexedPlayer : playerList)
				if (!indexedPlayer.equals(cityPlayer) && !indexedPlayer.equals(bayPlayer)) {
					indexedPlayer.addHealth(attack);
				}
		} else if (bayPlayer != null) {
			cityPlayer.addHealth(attack);
			bayPlayer.addHealth(attack);
		} else {
			cityPlayer.addHealth(attack);
		}
	}

	public void initializeDeck() {
		deck.createDeck();
		deck.shuffle();
	}

}
