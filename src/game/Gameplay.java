package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import cards.Card;
import cards.DeckConstructor;
import main.GUI;
import main.Messages;

public class Gameplay {

	public Player currentplayer;
	public Board gameboard;
	public GUI gameUI;
	public DeckConstructor deck;
	public HashMap<String, Integer> playerToNumber;
	public boolean redoTurn;
	public boolean cede;

	public Gameplay(GUI gui, Player player, Board board, DeckConstructor deck, HashMap<String, Integer> map) {
		this.gameUI = gui;
		this.currentplayer = player;
		this.gameboard = board;
		this.deck = deck;
		this.playerToNumber = map;
		if(this.deck==null) {
			this.deck = gameboard.deck;
		}
		if (playerToNumber==null) {
			playerToNumber = new HashMap<String, Integer>();
		}
	}

	public void initializeGame(Board board) {
		this.gameboard = board;
		ArrayList<String> names = gameUI.inputNames(board.numOfPlayers);
		gameboard.constructPlayers(names);
		for (int i = 0; i < board.numOfPlayers; i++) {
			playerToNumber.put(names.get(i), i);
		}
		deck.createDeck();
		deck.reveal();
		gameUI.displayBoard(gameboard, board.numOfPlayers, this);
		gameUI.setCards(deck.visibleCard);

	}

	public void beginGame() {
		selectFirstPlayer();
		gameUI.displayStartingPlayer(currentplayer.name);
		beginTurn();
	}

	public void beginTurn() {
		currentplayer.wings = false;
		gameUI.checkEnableButton(currentplayer);
		gameUI.setActivePlayer(playerToNumber.get(currentplayer.name));
		gameUI.DisableEndTurnButton();
		if (gameboard.cityPlayer == currentplayer) {
			currentplayer.addVictory(2);
		}else if (gameboard.cityPlayer == null) {
			gameboard.cityPlayer = currentplayer;
			gameUI.moveToTokyo(currentplayer);
			currentplayer.addVictory(1);
		}
		gameUI.updatePlayerText(gameboard);
		gameUI.DisableCedeButton();
		gameUI.EnableRollButton();
	}

	public void diceRolled(ArrayList<Dice> dicelist, Messages message) {
		
		ArrayList<Dice> otherdice = new ArrayList<Dice>();
		int attack = 0;
		int heal = 0;
		int energy = 0;
		
		for (Dice die : dicelist) {
			if (die.numberRolled == 4) {
				attack++;
			} else if (die.numberRolled == 6) {
				gameUI.HealingRay(this.currentplayer, this.gameboard);
				heal++;
			} else if (die.numberRolled == 5) {
				energy++;
			} else {
				otherdice.add(die);
			}
		}
		gameboard.doAttack(currentplayer, -attack);
		if (currentplayer != gameboard.cityPlayer){
			currentplayer.addHealth(heal);
		}
		currentplayer.addEnergy(energy);
		calculateScore(otherdice);
		gameUI.EnableEndTurnButton();
		gameUI.updatePlayerText(gameboard);
		gameUI.DisableRollButton();
		checkWin();
	}

	public void calculateScore(ArrayList<Dice> dice) {
		int count3 = 0;
		int count2 = 0;
		int count1 = 0;
		for (Dice die : dice) {
			int number = die.numberRolled;
			if (number == 1) {
				count1++;
			} else if (number == 2) {
				count2++;
			} else if (number == 3) {
				count3++;
			}
		}
		if (count3 > 2) {
			currentplayer.addVictory(3 + (count3 - 3));
		}
		if (count2 > 2) {
			currentplayer.addVictory(2 + (count2 - 3));
		}
		if (count1 > 2) {
			currentplayer.addVictory(1 + (count1 - 3));
		}
		
	}

	public void endTurn() {
		String currentPlayerName = currentplayer.name;
		currentplayer.extraDie = 0;
		currentplayer.extraRoll = 0;
		currentplayer.herdCuller = true;
		if(redoTurn) {
			redoTurn = false;
			gameUI.replaceDice();
			beginTurn();
			return;
		}
		
		int index;
		if (playerToNumber.get(currentPlayerName) >= (gameboard.numOfPlayers - 1)) {
			index = 0;
		} else {
			index = playerToNumber.get(currentPlayerName) + 1;
		}
		
		while(gameboard.playerList.get(index).playerDeathThisTurn) {
			index++;
			if(index >= gameboard.numOfPlayers) {
				index = 0;
			}
		}
		
		currentplayer = gameboard.playerList.get(index);
		
		gameUI.replaceDice();
		beginTurn();
	}
	
	public void buyCard(int number) {
		Card tobuy = deck.visibleCard[number-1];
		if(currentplayer.energy >=tobuy.cost) {
			Card card = deck.buy(number-1);
			currentplayer.addEnergy(-tobuy.cost);
			currentplayer.addToHand(card);
			if(card.type.equals("Discard") || card.name.equals("Even Bigger") || card.name.equals("Battery Monster")) {
				this.useCard(card.name);
			}
			gameUI.setCards(deck.visibleCard);
			gameUI.updatePlayerText(gameboard);
		} else {
			gameUI.energyWarning();
		}
	}

	public void selectFirstPlayer() {
		ArrayList<Player> playerlist = gameboard.playerList;
		Integer numOfPlayers = gameboard.numOfPlayers;
		Random random = new Random();
		Integer firstplayer = random.nextInt(numOfPlayers);
		this.currentplayer = playerlist.get(firstplayer);
	}

	public void cedeTokyo() {
		if(gameboard.cityPlayer == currentplayer){
			return;
		}
		if(gameboard.cityPlayer.haveCard("Burrowing")) {
			currentplayer.addHealth(-1);
		}
		gameboard.cityPlayer = currentplayer;
		gameUI.moveToTokyo(currentplayer);
		currentplayer.addVictory(1);
		gameUI.updatePlayerText(gameboard);
		gameUI.DisableCedeButton();
		cede = true;
	}
	
	public void cedeBay() {
		if(gameboard.bayPlayer == currentplayer){
			return;
		}
		if(gameboard.bayPlayer.haveCard("Burrowing")) {
			currentplayer.addHealth(-1);
		}
		gameboard.cityPlayer = currentplayer;
		gameUI.moveToBay(currentplayer);
		currentplayer.addVictory(1);
		gameUI.updatePlayerText(gameboard);
		gameUI.DisableCedeButton();
		cede = true;
	}

	public void swipeCard() {
		int playerEnergy = currentplayer.energy;
		if(playerEnergy >= 2) {
			deck.swipe();
			gameUI.setCards(deck.visibleCard);
			currentplayer.addEnergy(-2);
			gameUI.updatePlayerText(gameboard);
		} else {
			gameUI.energyWarning();
		}
	}

	public void useCard(String cardname) {
		Card touse = null;
		ArrayList<Card> cards = currentplayer.cardsInHand;
		for(Card card : cards) {
			if(card.name.equals(cardname)) {
				touse = card;
				break;
			}
		}
		if(touse!=null) {
			try {
				touse.logic.use(currentplayer, gameboard.playerList, gameUI);
			}catch(UnsupportedOperationException e){
				gameUI.cardCannotUseWarning();
			}
			if(touse.type.equals("Discard")) {
				currentplayer.cardsInHand.remove(touse);
				deck.addToDiscard(touse);
			}
		}
		gameUI.updatePlayerText(gameboard);
	}

	public void checkWin() {
		if((currentplayer.victoryPoints >=20 && currentplayer.health > 0)) {
			gameUI.endGame(currentplayer, 1);
			return;
		}
		int total = 0;
		for(Player player : gameboard.playerList) {
			if(player.health > 0) {
				total++;
			}
		}
		if(total == 1) {
			gameUI.endGame(currentplayer, 2);
			return;
		}
		
	}
}
